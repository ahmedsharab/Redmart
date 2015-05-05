package co.nz.kastana.android.redmart.assignment.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.inject.Inject;
import com.squareup.otto.Subscribe;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import co.nz.kastana.android.redmart.assignment.R;
import co.nz.kastana.android.redmart.assignment.adapter.ProductListAdapter;
import co.nz.kastana.android.redmart.assignment.model.ProductList;
import co.nz.kastana.android.redmart.assignment.service.event.ProductListErrorEvent;
import co.nz.kastana.android.redmart.assignment.service.event.ProductListReadyEvent;
import co.nz.kastana.android.redmart.assignment.service.remote.ProductListRemoteService;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static co.nz.kastana.android.redmart.assignment.R.id.cardList;
import static co.nz.kastana.android.redmart.assignment.R.layout.activity_main;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_IMAGE;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_PRODUCT;

@ContentView(activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.viewAnimator)  private ViewAnimator viewAnimator;
    @InjectView(cardList)           private RecyclerView mRecyclerView;
    @Inject                         private ProductListRemoteService productListRemoteService;

    private List<ProductList> products = new ArrayList<ProductList>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus.unRegister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productListRemoteService.getProductsList();
    }
    @Subscribe
    public void onProductListErrorEvent(ProductListErrorEvent errorEvent){
        Toast.makeText(this, errorEvent.getMessage(), Toast.LENGTH_LONG).show();
    }
    @Subscribe
    public void onProductListReadyEvent(ProductListReadyEvent readyEvent){
        products = readyEvent.getResponse().getProducts();
        populateList(products);
        showProductList();
    }

    public void animateActivity(ImageView imageView, int position) {
        Intent i = new Intent(MainActivity.this, ProductDetailsActivity.class);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ((BitmapDrawable)imageView.getDrawable() ).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        i.putExtra(ARG_IMAGE, stream.toByteArray());
        i.putExtra(ARG_PRODUCT, products.get(position));
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, imageView, "appIcon");
        startActivity(i, transitionActivityOptions.toBundle());
    }

    private void populateList(List<ProductList> productList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.cardList);
//        use this setting to improve performance if you know that changes
//        in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        mAdapter = new ProductListAdapter(productList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void showProductList(){
        viewAnimator.setDisplayedChild(1);
    }


}
