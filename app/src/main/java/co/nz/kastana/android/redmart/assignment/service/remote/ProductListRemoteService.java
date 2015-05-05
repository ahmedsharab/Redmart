package co.nz.kastana.android.redmart.assignment.service.remote;

import co.nz.kastana.android.redmart.assignment.model.ProductListResponse;
import co.nz.kastana.android.redmart.assignment.service.abs.AbstractRemoteEndpointProvider;
import co.nz.kastana.android.redmart.assignment.service.abs.BaseRemoteService;
import co.nz.kastana.android.redmart.assignment.service.abs.RemoteOperation;
import retrofit.Callback;
import retrofit.http.GET;

public class ProductListRemoteService extends BaseRemoteService<ProductListRemoteService.GetListRemoteEndpoint, ProductListCallback> {

    public void getProductsList(){
        execute(new RemoteOperation<GetListRemoteEndpoint, ProductListCallback>() {
            @Override
            public void execute(GetListRemoteEndpoint endpoint, ProductListCallback callback) {
                endpoint.getProductList(callback);
            }
        });
    }

    @Override
    public ProductListCallback getCallback() {
        return getInstance(ProductListCallback.class);
    }

    @Override
    protected GetListRemoteEndpoint getEndpoint() {
        return getInstance(GetListRemoteEndpointProvider.class).get();
    }

    public static class GetListRemoteEndpointProvider extends AbstractRemoteEndpointProvider<GetListRemoteEndpoint> {
        @Override
        protected Class<GetListRemoteEndpoint> getEndpointClass() {
            return GetListRemoteEndpoint.class;
        }
    }

    public interface GetListRemoteEndpoint {
        @GET("/products/new?page=0&pageSize=20&sort=null&instock=false")
        void getProductList(Callback<ProductListResponse> callback);
    }
}
