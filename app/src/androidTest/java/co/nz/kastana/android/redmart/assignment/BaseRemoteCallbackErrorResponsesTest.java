/**
 * This tests that the service send correct error events with correct parameters when:
 * - Error code and Wrapped response with correct error code and message
 * - Error code and Wrapped response with correct error code and empty message
 * - Error code and Unwrapped response with correct error code and message
 * - Error code and Unwrapped response with correct error code and empty message
 * - Error code and Null response
 * - Error code and empty response
 * - Error code and incorrect json
 */
package co.nz.kastana.android.redmart.assignment;

import android.test.suitebuilder.annotation.SmallTest;

import org.mockito.ArgumentCaptor;

import co.nz.kastana.android.redmart.assignment.service.event.ProductListErrorEvent;
import co.nz.kastana.android.redmart.assignment.service.remote.ProductListRemoteService;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.verify;

@SmallTest
public class BaseRemoteCallbackErrorResponsesTest extends AbstractBaseRemoteServiceTest {

    String defaultMessage;
    ProductListRemoteService getProductListService;
    ArgumentCaptor<ProductListErrorEvent> captor = forClass(ProductListErrorEvent.class);

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getProductListService = getInstance(ProductListRemoteService.class);
        defaultMessage = "error getting service";
    }

    public void testErrorEventSentWhenWrappedResponseWithCorrectErrorCodeAndMessage() {
        // Given
        String aMessage = "uiMessage";
        int errorCode = HTTP_500;
        String apiErrorWrapped = String.format(CommonFixture.API_ERROR_WRAPPED_TEMPLATE, errorCode, aMessage);
        testClient.addResponse(TestClient.response(errorCode, apiErrorWrapped));

        // When
        doRequest();

        // Then
        assertErrorEventSentWith(aMessage, errorCode);
    }

    private void doRequest() {
        getProductListService.getProductsList();
    }

    private void assertErrorEventSentWith(String message, int code) {
        verify(bus).publish(captor.capture());
        ProductListErrorEvent event = captor.getValue();
        assertNotNull(event);
        assertEquals(message, event.getMessage());
        assertEquals(code, event.getCode());
    }
}
