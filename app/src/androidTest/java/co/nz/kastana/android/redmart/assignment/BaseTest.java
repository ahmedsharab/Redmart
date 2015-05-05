package co.nz.kastana.android.redmart.assignment;


import android.app.Application;
import android.test.InstrumentationTestCase;

import com.google.inject.Binder;
import com.google.inject.Module;

import roboguice.config.DefaultRoboModule;

import static roboguice.RoboGuice.getInjector;
import static roboguice.RoboGuice.newDefaultRoboModule;
import static roboguice.RoboGuice.overrideApplicationInjector;

public abstract class BaseTest extends InstrumentationTestCase {

    protected Application application;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setUpInjections();
        getInjector(application).injectMembers(this);
    }

    private void setUpInjections() {
        application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
        DefaultRoboModule module = newDefaultRoboModule(application);
        overrideApplicationInjector(application, module, getMocksModule());
    }

    protected Module getMocksModule() {
        return new Module() {
            @Override
            public void configure(Binder binder) {
            }
        };
    }
}