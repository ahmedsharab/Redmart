package co.nz.kastana.android.redmart.assignment.service.event;

import co.nz.kastana.android.redmart.assignment.model.ProductListResponse;
import co.nz.kastana.android.redmart.assignment.service.abs.SuccessEvent;

public class ProductListReadyEvent implements SuccessEvent {

    private ProductListResponse response;

    public ProductListReadyEvent(ProductListResponse response) {
        this.response = response;
    }

    public ProductListResponse getResponse() {
        return response;
    }
}