package co.nz.kastana.android.redmart.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ahmed on 4/05/15.
 */
public class Image implements Serializable{
    @SerializedName("h")
    private int height;
    @SerializedName("w")
    private int width;
    private int position;
    private String name;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
