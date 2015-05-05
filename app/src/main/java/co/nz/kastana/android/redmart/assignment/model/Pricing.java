package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Pricing implements Serializable{

    private double price;
    @SerializedName("promo_price")
    private double promoPrice;
    private double savings;
    @SerializedName("on_sale")
    private double onSale;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getOnSale() {
        return onSale;
    }

    public void setOnSale(double onSale) {
        this.onSale = onSale;
    }
}
