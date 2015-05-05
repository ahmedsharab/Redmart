package co.nz.kastana.android.redmart.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.nz.kastana.android.redmart.assignment.R;
import co.nz.kastana.android.redmart.assignment.activity.MainActivity;
import co.nz.kastana.android.redmart.assignment.model.ProductList;
import co.nz.kastana.android.redmart.assignment.util.Utility;

import static co.nz.kastana.android.redmart.assignment.R.string.uri_images_parent;
/**
 * Created by ahmed on 4/05/15.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ProductList> mDataset;
    private MainActivity mAct;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final TextView descTextView;
        public final TextView priceTextView;
        public final ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            descTextView = (TextView) v.findViewById(R.id.TextView_Desc);
            priceTextView = (TextView) v.findViewById(R.id.TextView_Price);
            mImageView = (ImageView) v.findViewById(R.id.appIcon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductListAdapter(List<ProductList> myDataset, MainActivity mAct) {
        mDataset = myDataset;
        this.mAct = mAct;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Context context = holder.mImageView.getContext();
        holder.descTextView.setText(mDataset.get(position).getTitle());
        holder.priceTextView.setText(getStringRes(context, R.string.txt_price, Utility.convertToTwoDecimals(mDataset.get(position).getPricing().getPrice())));
        Picasso.with(holder.mImageView.getContext()).
                load(new StringBuilder().append(getStringRes(context, uri_images_parent, null)).append(mDataset.get(position).getImage().getName()).toString()).
                into(holder.mImageView);
        ((ViewGroup)holder.mImageView.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAct.animateActivity(holder.mImageView,position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String getStringRes(Context context, int StringResId, String params){
        if(params != null){
            return context.getResources().getString(StringResId, params);
        }
        return context.getResources().getString(StringResId);
    }
}
