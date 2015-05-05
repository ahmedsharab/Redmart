package co.nz.kastana.android.redmart.assignment.service.remote;

import android.content.Context;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import co.nz.kastana.android.redmart.assignment.model.ProductListResponse;
import co.nz.kastana.android.redmart.assignment.service.abs.BaseRemoteCallback;
import co.nz.kastana.android.redmart.assignment.service.abs.ErrorEvent;
import co.nz.kastana.android.redmart.assignment.service.event.ProductListErrorEvent;
import co.nz.kastana.android.redmart.assignment.service.event.ProductListReadyEvent;

import static co.nz.kastana.android.redmart.assignment.R.string.error_generic_getProductList;

public class ProductListCallback extends BaseRemoteCallback<ProductListResponse> {
    private String genericErrorMessage;

    @Inject
    public ProductListCallback(Context context) {
        genericErrorMessage = context.getString(error_generic_getProductList);
    }

    @Override
    protected ErrorEvent getGenericErrorEvent(String message) {
        return new ProductListErrorEvent(genericErrorMessage);
    }

    @Override
    protected String getGenericErrorMessage() {
        return genericErrorMessage;
    }

    @Override
    protected void onSuccess(Optional<ProductListResponse> object) {
        if (object.isPresent()) {
            ProductListResponse customerResponse = object.get();
            publish(new ProductListReadyEvent(customerResponse));
        } else {
            publish(new ProductListErrorEvent(genericErrorMessage));
        }
    }
}
