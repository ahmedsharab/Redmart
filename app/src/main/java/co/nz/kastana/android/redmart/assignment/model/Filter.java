package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Filter implements Serializable {
    @SerializedName("is_organic")
    private int isOrganic;
    @SerializedName("brand_uri")
    private String brandUri;
    @SerializedName("mfr_name")
    private String mFRName;
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("vendor_name")
    private String vendorName;
    private int frequency;
    private int vendor;

    public boolean isOrganic() {
        return isOrganic == 1;
    }

    public void setIsOrganic(int isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getBrandUri() {
        return brandUri;
    }

    public void setBrandUri(String brandUri) {
        this.brandUri = brandUri;
    }

    public String getmFRName() {
        return mFRName;
    }

    public void setmFRName(String mFRName) {
        this.mFRName = mFRName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getVendor() {
        return vendor;
    }

    public void setVendor(int vendor) {
        this.vendor = vendor;
    }
}
