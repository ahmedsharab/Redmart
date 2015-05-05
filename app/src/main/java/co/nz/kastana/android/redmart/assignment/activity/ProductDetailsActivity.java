package co.nz.kastana.android.redmart.assignment.activity;

import android.animation.Animator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;

import co.nz.kastana.android.redmart.assignment.R;
import co.nz.kastana.android.redmart.assignment.adapter.ImageDepthPageTransformer;
import co.nz.kastana.android.redmart.assignment.adapter.ImageSliderAdapter;
import co.nz.kastana.android.redmart.assignment.listener.ImageSlideListener;
import co.nz.kastana.android.redmart.assignment.model.ProductList;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static co.nz.kastana.android.redmart.assignment.R.layout.activity_product;
import static co.nz.kastana.android.redmart.assignment.R.string.txt_insertDollarSign;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_IMAGE;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_PRODUCT;
import static co.nz.kastana.android.redmart.assignment.util.Utility.convertPixelsToDp;
import static co.nz.kastana.android.redmart.assignment.util.Utility.convertToTwoDecimals;

@ContentView(activity_product)
public class ProductDetailsActivity extends BaseActivity {

    private static final int SCALE_DELAY = 30;

    @InjectView(R.id.appIcon) private ImageView imageView;
    @InjectView(R.id.row_container) private LinearLayout rowContainer;

    private ProductList product;
    private SparseArray<View> indicators = new SparseArray<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ViewPager mPager;
    private static View.OnClickListener indicatorOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPager.setCurrentItem((Integer) view.getTag());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        product = (ProductList) getIntent().getExtras().get(ARG_PRODUCT);
        setUpSharedImage();
        setUpProductDetails();
        setUpViewPager();
        setUpImageSelectionIndicator();
        showViewPager();
    }

    @Override
    public void onBackPressed() {
        mPager.setCurrentItem(0);
        mPager.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
        animateExitContainerItems();
    }

    private void setUpImageSelectionIndicator() {
        LinearLayout indicatorAnchor = (LinearLayout) findViewById(R.id.pagerIndicator);
        for(int i = 0 ; i < product.getImages().size(); i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(convertPixelsToDp(100, this), convertPixelsToDp(100, this));
            View view = new View(this);
            if(i==0)
                view.setBackgroundResource(R.drawable.indicator_fill);
            else
                view.setBackgroundResource(R.drawable.indicator);
            params.setMargins(convertPixelsToDp(50, this),convertPixelsToDp(10, this),convertPixelsToDp(50, this),convertPixelsToDp(10, this));
            view.setLayoutParams(params);
            view.setTag(new Integer(i));
            view.setOnClickListener(indicatorOnClickListener);
            indicatorAnchor.addView(view);
            indicators.put(indicators.size(), view);
        }
    }

    private byte[] setUpSharedImage() {
        byte[] byteArray = getIntent().getByteArrayExtra(ARG_IMAGE);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bitmap);
        return byteArray;
    }

    private void setUpViewPager() {
        PagerAdapter mPagerAdapter = new ImageSliderAdapter(getSupportFragmentManager(), product, getIntent().getByteArrayExtra(ARG_IMAGE));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ImageDepthPageTransformer());
        mPager.setOnPageChangeListener(new ImageSlideListener(indicators));
    }

    private void setUpProductDetails() {
        // Row Container
        for (int i = 1; i < rowContainer.getChildCount(); i++) {
            View rowView = rowContainer.getChildAt(i);
            rowView.animate().setStartDelay(100 + i * SCALE_DELAY).scaleX(1).scaleY(1);
        }

        View view = rowContainer.findViewById(R.id.row_title);
        fillRow(view, "Title", product.getTitle());
        view = rowContainer.findViewById(R.id.row_pricing);
        fillRow(view, "Price", getPricing(product));
        view = rowContainer.findViewById(R.id.row_sku);
        fillRow(view, "SKU", product.getSku());
        view = rowContainer.findViewById(R.id.row_desc);
        fillRow(view, "Description", product.getDescription());
        view = rowContainer.findViewById(R.id.row_inventory);
        fillRow(view, "Inventory", getInventory(product));
    }

    private String getPricing(ProductList product){
        StringBuilder builder = new StringBuilder();
        builder.append("Price: ");
        builder.append(getString(txt_insertDollarSign, convertToTwoDecimals(product.getPricing().getPrice())));
        builder.append("\n");
        builder.append("Promo Price: ");
        builder.append(getString(txt_insertDollarSign, convertToTwoDecimals(product.getPricing().getPromoPrice())));
        builder.append("\n");
        builder.append("On Sale: ");
        builder.append(getString(txt_insertDollarSign, convertToTwoDecimals(product.getPricing().getOnSale())));
        builder.append("\n");
        builder.append("Savings: ");
        builder.append(getString(txt_insertDollarSign, convertToTwoDecimals(product.getPricing().getSavings())));
        return builder.toString();
    }

    private String getInventory(ProductList product){
        StringBuilder builder = new StringBuilder();
        builder.append("Quantity in Cart: ");
        builder.append(product.getInventory().getQuantityInCarts());
        builder.append("\n");
        builder.append("Quantity in Stock: ");
        builder.append(product.getInventory().getQuantityInStock());
        builder.append("\n");
        return builder.toString();
    }

    private void showViewPager() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPager.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
            }
        }, (getResources().getInteger(R.integer.transaction_speed)*2));
    }

    private void animateExitContainerItems(){
        for (int i = rowContainer.getChildCount() - 1; i > 0; i--) {
            View rowView = rowContainer.getChildAt(i);
            ViewPropertyAnimator propertyAnimator = rowView.animate().setStartDelay((rowContainer.getChildCount() - 1 - i) * SCALE_DELAY)
                    .scaleX(0).scaleY(0);
            propertyAnimator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
                @Override
                public void onAnimationCancel(Animator animator) {
                }
                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
        }
    }

    private void fillRow(View view, final String title, final String description) {
        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(title);

        TextView descriptionView = (TextView) view.findViewById(R.id.description);
        descriptionView.setText(description);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("AppInfo", description);
                clipboard.setPrimaryClip(clip);

                Snackbar.with(getApplicationContext()).dismiss();
                Snackbar.with(getApplicationContext()) // context
                        .text("Copied " + title) // text to display
                        .show(ProductDetailsActivity.this);
            }
        });
    }

}
