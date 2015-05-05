package co.nz.kastana.android.redmart.assignment;

import android.test.suitebuilder.annotation.SmallTest;

import org.mockito.ArgumentCaptor;

import java.io.UnsupportedEncodingException;

import co.nz.kastana.android.redmart.assignment.service.event.ProductListErrorEvent;
import co.nz.kastana.android.redmart.assignment.service.event.ProductListReadyEvent;
import co.nz.kastana.android.redmart.assignment.service.remote.ProductListRemoteService;

import static co.nz.kastana.android.redmart.assignment.TestClient.response;
import static co.nz.kastana.android.redmart.assignment.TestClient.responseWithNullBody;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.verify;
import static roboguice.RoboGuice.getInjector;

@SmallTest
public class ProductListRemoteServiceTest extends AbstractBaseRemoteServiceTest {

    static final String URL = BuildConfig.ENDPOINT + "/products/new?page=0&pageSize=20&sort=null&instock=false";

    private ProductListRemoteService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        service = getInjector(application).getInstance(ProductListRemoteService.class);
    }

    public void testGetProductListRequest() throws UnsupportedEncodingException {
        testClient.addResponse(TestClient.response(HTTP_200, CommonFixture.JSON_RESPONSE_PRODUCT_LIST_SUCCESS));
        // When
        service.getProductsList();
        // Then
        assertEquals(URL, getRequest().getUrl());
    }

    public void testProductsReturnedWhenResponseIsCorrect() {
        // Given
        testClient.addResponse(response(HTTP_200, CommonFixture.JSON_RESPONSE_PRODUCT_LIST_SUCCESS));
        // When
        service.getProductsList();
        // Then
        ArgumentCaptor<ProductListReadyEvent> captor = forClass(ProductListReadyEvent.class);
        verify(bus).publish(captor.capture());
        ProductListReadyEvent actualArgument = captor.getValue();
        assertNotNull(actualArgument);
        assertEquals(19, actualArgument.getResponse().getProducts().size());
    }

    public void testChangePasswordErrorEventIsPublishedWhenResponseIs500AndResponseIsNull() {
        // Given
        ArgumentCaptor<ProductListErrorEvent> captor = forClass(ProductListErrorEvent.class);
        testClient.addResponse(responseWithNullBody(HTTP_500));

        // When
        service.getProductsList();

        // Then
        String expectedString = getString(R.string.error_generic_getProductList);

        verify(bus).publish(captor.capture());
        ProductListErrorEvent event = captor.getValue();
        assertEquals(expectedString, event.getMessage());
    }
}