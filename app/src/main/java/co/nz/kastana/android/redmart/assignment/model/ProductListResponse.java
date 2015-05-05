package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 2/05/15.
 */
public class ProductListResponse {

    private int total;
    @SerializedName("page_size")
    private int pageSize;
    private int page;
    private String session;
    private List<ProductList> products;
    private Status status;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<ProductList> getProducts() {
        return products;
    }

    public void setProducts(List<ProductList> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
