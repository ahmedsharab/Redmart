package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Details implements Serializable{

    @SerializedName("storage_class")
    private String storageClass;
    @SerializedName("prod_type")
    private int productType;
    @SerializedName("is_new")
    private int isNew;
    private int status;
    private String uri;

    public boolean isNew() {
       return isNew == 1;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
