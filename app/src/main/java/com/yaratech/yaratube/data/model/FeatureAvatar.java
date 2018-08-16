
package com.yaratech.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.yaratech.yaratube.utils.Constant.BASE_URL;

public class FeatureAvatar {

    @SerializedName("mdpi")
    @Expose
    private String mdpi;
    @SerializedName("xxxdpi")
    @Expose
    private String xxxdpi;
    @SerializedName("xhdpi")
    @Expose
    private String xhdpi;
    @SerializedName("xxhdpi")
    @Expose
    private String xxhdpi;
    @SerializedName("hdpi")
    @Expose
    private String hdpi;

    public String getMdpi() {
        return mdpi;
    }

    public void setMdpi(String mdpi) {
        this.mdpi = mdpi;
    }

    public String getXxxdpi() {
        return BASE_URL+'/'+xxxdpi;
    }

    public void setXxxdpi(String xxxdpi) {
        this.xxxdpi = xxxdpi;
    }

    public String getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(String xhdpi) {
        this.xhdpi = xhdpi;
    }

    public String getXxhdpi() {
        return BASE_URL+ xxhdpi;
    }

    public void setXxhdpi(String xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public String getHdpi() {
        return hdpi;
    }

    public void setHdpi(String hdpi) {
        this.hdpi = hdpi;
    }

}
