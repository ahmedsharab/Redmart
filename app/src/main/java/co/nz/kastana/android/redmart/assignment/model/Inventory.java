package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ahmed on 4/05/15.
 */
public class Inventory implements Serializable {
    @SerializedName("atp_lots")
    private List<Integer> atpLots;
    @SerializedName("atp_status")
    private int atpStatus;
    @SerializedName("max_sale_qty")
    private int maxSaleQuantity;
    @SerializedName("qty_in_carts")
    private int quantityInCarts;
    @SerializedName("qty_in_stock")
    private int quantityInStock;
    @SerializedName("stock_status")
    private int stockStatus;

    public List<Integer> getAtpLots() {
        return atpLots;
    }

    public void setAtpLots(List<Integer> atpLots) {
        this.atpLots = atpLots;
    }

    public int getAtpStatus() {
        return atpStatus;
    }

    public void setAtpStatus(int atpStatus) {
        this.atpStatus = atpStatus;
    }

    public int getMaxSaleQuantity() {
        return maxSaleQuantity;
    }

    public void setMaxSaleQuantity(int maxSaleQuantity) {
        this.maxSaleQuantity = maxSaleQuantity;
    }

    public int getQuantityInCarts() {
        return quantityInCarts;
    }

    public void setQuantityInCarts(int quantityInCarts) {
        this.quantityInCarts = quantityInCarts;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }
}
