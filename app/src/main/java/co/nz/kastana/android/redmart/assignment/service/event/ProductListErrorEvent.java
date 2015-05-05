package co.nz.kastana.android.redmart.assignment.service.event;

import co.nz.kastana.android.redmart.assignment.service.abs.AbstractErrorEvent;

public class ProductListErrorEvent extends AbstractErrorEvent {

    public ProductListErrorEvent() {
    }

    public ProductListErrorEvent(String message) {
        super(message);
    }
}
