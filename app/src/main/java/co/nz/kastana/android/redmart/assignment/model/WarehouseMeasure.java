package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class WarehouseMeasure implements Serializable {
    @SerializedName("vol")
    private int volume;
    @SerializedName("wt")
    private double weight;
    @SerializedName("w")
    private double width;
    @SerializedName("h")
    private double height;
    @SerializedName("l")
    private double length;
    @SerializedName("wt_metric")
    private String weightMetric;
    @SerializedName("vol_metric")
    private String volumeMetric;

}
