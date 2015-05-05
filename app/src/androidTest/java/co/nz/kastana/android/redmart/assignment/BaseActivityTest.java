package co.nz.kastana.android.redmart.assignment;

import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;

import com.google.inject.Module;

import roboguice.config.DefaultRoboModule;

import static roboguice.RoboGuice.destroyInjector;
import static roboguice.RoboGuice.newDefaultRoboModule;
import static roboguice.RoboGuice.overrideApplicationInjector;

/**
 * Created by ahmed on 5/05/15.
 */
public abstract class BaseActivityTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public BaseActivityTest(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setUpInjections();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
        destroyInjector(application);
    }

    private void setUpInjections() {
        Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
        DefaultRoboModule module = newDefaultRoboModule(application);
        overrideApplicationInjector(application, getMocksModule());
    }

    protected abstract Module getMocksModule();
}
