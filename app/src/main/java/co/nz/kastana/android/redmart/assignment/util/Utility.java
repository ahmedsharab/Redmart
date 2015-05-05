package co.nz.kastana.android.redmart.assignment.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;

/**
 * Created by ahmed on 4/05/15.
 */
public class Utility {
    public static String convertToTwoDecimals(Double number){
        DecimalFormat df = new DecimalFormat("0.00##");
        try {
            return df.format(number);
        }
        catch (NumberFormatException ex){
            return number.toString();
        }
    }

    public static int convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp = (int)(px / (metrics.densityDpi / 160f));
        return dp;
    }
}
