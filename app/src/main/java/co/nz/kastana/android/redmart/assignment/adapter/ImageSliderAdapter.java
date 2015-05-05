package co.nz.kastana.android.redmart.assignment.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.nz.kastana.android.redmart.assignment.fragment.ImageFragment;
import co.nz.kastana.android.redmart.assignment.model.ProductList;

import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_IMAGE;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_URL;

/**
 * Created by ahmed on 4/05/15.
 */
public class ImageSliderAdapter extends FragmentStatePagerAdapter {
    private byte[] byteArray;
    private ProductList product;

    public ImageSliderAdapter(FragmentManager fm, ProductList product, byte[] byteArray) {
        super(fm);
        this.byteArray = byteArray;
        this.product = product;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putByteArray(ARG_IMAGE, byteArray);
        } else {
            bundle.putString(ARG_URL, product.getImages().get(position).getName());
        }
        return new ImageFragment().instantiate(bundle);
    }

    @Override
    public int getCount() {
        return product.getImages().size();
    }
}
