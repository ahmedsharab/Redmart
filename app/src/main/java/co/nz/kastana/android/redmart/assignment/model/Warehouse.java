package co.nz.kastana.android.redmart.assignment.model;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Warehouse implements Serializable {
    private WarehouseMeasure measure;

    public WarehouseMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(WarehouseMeasure measure) {
        this.measure = measure;
    }
}
