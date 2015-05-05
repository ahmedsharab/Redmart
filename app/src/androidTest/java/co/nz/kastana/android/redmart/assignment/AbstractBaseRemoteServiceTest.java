package co.nz.kastana.android.redmart.assignment;

import android.content.res.Resources;

import com.google.inject.Binder;
import com.google.inject.Module;

import co.nz.kastana.android.redmart.assignment.roboguice.EventBus;
import retrofit.RestAdapter;
import retrofit.client.Request;

import static org.mockito.Mockito.mock;
import static roboguice.RoboGuice.getInjector;

public abstract class AbstractBaseRemoteServiceTest extends BaseTest {

    protected static final int HTTP_200 = 200;
    protected static final int HTTP_500 = 500;

    protected EventBus bus;
    protected TestClient testClient;

    @Override
    public void setUp() throws Exception {
        bus = mock(EventBus.class);
        super.setUp();
        testClient = getInjector(application).getInstance(TestClient.class);
    }

    @Override
    protected Module getMocksModule() {
        return new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(EventBus.class).toInstance(bus);
                binder.bind(TestClient.class).asEagerSingleton();
                binder.bind(RestAdapter.Builder.class)
                        .toProvider(TestRestAdapterBuilderProvider.class).asEagerSingleton();
            }
        };
    }

    protected <T> T getInstance(Class<T> clazz) {
        return getInjector(application).getInstance(clazz);
    }

    protected int getInteger(int resId) {
        return getResources().getInteger(resId);
    }

    protected String getString(int resId) {
        return getResources().getString(resId);
    }

    private Resources getResources() {
        return getInstrumentation().getTargetContext().getResources();
    }

    protected Request getRequest() {
        return testClient.getRequest(0);
    }
}