package co.nz.kastana.android.redmart.assignment.listener;

import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;

import co.nz.kastana.android.redmart.assignment.R;

/**
 * Created by ahmed on 4/05/15.
 */
public class ImageSlideListener implements ViewPager.OnPageChangeListener {

    private SparseArray<View> indicators;
    public  ImageSlideListener(SparseArray<View> indicators){
        this.indicators = indicators;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i=0; i < indicators.size(); i++){
            if(i != position)
                indicators.get(i).setBackgroundResource(R.drawable.indicator);
            else
                indicators.get(i).setBackgroundResource(R.drawable.indicator_fill);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
