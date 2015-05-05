package co.nz.kastana.android.redmart.assignment;

import android.test.suitebuilder.annotation.SmallTest;

import com.google.common.base.Optional;

import co.nz.kastana.android.redmart.assignment.service.abs.AbstractCallback;
import co.nz.kastana.android.redmart.assignment.service.abs.BaseRemoteService;
import co.nz.kastana.android.redmart.assignment.service.abs.RemoteOperation;
import retrofit.RetrofitError;

import static org.mockito.Mockito.mock;
import static roboguice.RoboGuice.getInjector;

@SmallTest
public class BaseRemoteServiceTest extends AbstractBaseRemoteServiceTest {

    MockRemoteService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        service = getInjector(application).getInstance(MockRemoteService.class);
    }

    public void testWaitingCallbackIsAbsentWhenObjectCreated() {
        // Expect
        assertFalse(service.waitingCallback.isPresent());
    }

    public void testCallbackIsCorrectlySetWhenExecute() {
        // Given
        RemoteOperation mockRemoteOperation = mock(RemoteOperation.class);

        // When
        service.execute(mockRemoteOperation);

        // Then
        assertTrue(service.waitingCallback.isPresent());
    }

    public void testOnCancelWaitingCallBackIsSetToAbsentAndCancelled() {
        // Given
        RemoteOperation mockRemoteOperation = mock(RemoteOperation.class);
        service.execute(mockRemoteOperation);

        MockCallback callback = service.waitingCallback.get();

        // When
        service.cancel();

        // Then
        assertTrue(callback.isCanceled());
        assertFalse(service.waitingCallback.isPresent());
    }

    public void testOnExecuteCalledTwiceFirstCallbackIsCancelled() {
        // Given
        RemoteOperation mockRemoteOperation1 = mock(RemoteOperation.class);
        RemoteOperation mockRemoteOperation2 = mock(RemoteOperation.class);

        service.execute(mockRemoteOperation1);

        MockCallback callback1 = service.waitingCallback.get();

        // When
        service.execute(mockRemoteOperation2);
        MockCallback callback2 = service.waitingCallback.get();

        // Then
        assertTrue(callback1.isCanceled());
        assertFalse(callback2.isCanceled());
    }

    static class MockCallback extends AbstractCallback {

        @Override
        protected void onSuccess(Optional object) {

        }

        @Override
        protected void onFailure(RetrofitError error) {

        }
    }

    interface MockEndpoint {

    }

    static class MockRemoteService extends BaseRemoteService<MockEndpoint, MockCallback> {
        @Override
        protected MockCallback getCallback() {
            return new MockCallback();
        }

    }
}
