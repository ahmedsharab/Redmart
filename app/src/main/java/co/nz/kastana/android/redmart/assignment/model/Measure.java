package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Measure implements Serializable{

    @SerializedName("wt_or_vol")
    private String weightOrVolume;
    private double size;

    public String getWeightOrVolume() {
        return weightOrVolume;
    }

    public void setWeightOrVolume(String weightOrVolume) {
        this.weightOrVolume = weightOrVolume;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
