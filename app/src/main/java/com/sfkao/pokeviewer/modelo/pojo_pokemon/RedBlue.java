
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RedBlue implements Serializable, Parcelable
{

    @SerializedName("back_default")
    @Expose
    private Object backDefault;
    @SerializedName("back_gray")
    @Expose
    private Object backGray;
    @SerializedName("back_transparent")
    @Expose
    private Object backTransparent;
    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_gray")
    @Expose
    private Object frontGray;
    @SerializedName("front_transparent")
    @Expose
    private Object frontTransparent;
    public final static Creator<RedBlue> CREATOR = new Creator<RedBlue>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RedBlue createFromParcel(android.os.Parcel in) {
            return new RedBlue(in);
        }

        public RedBlue[] newArray(int size) {
            return (new RedBlue[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8156673723950496839L;

    protected RedBlue(android.os.Parcel in) {
        this.backDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backGray = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backTransparent = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontGray = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontTransparent = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public RedBlue() {
    }

    public Object getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(Object backDefault) {
        this.backDefault = backDefault;
    }

    public Object getBackGray() {
        return backGray;
    }

    public void setBackGray(Object backGray) {
        this.backGray = backGray;
    }

    public Object getBackTransparent() {
        return backTransparent;
    }

    public void setBackTransparent(Object backTransparent) {
        this.backTransparent = backTransparent;
    }

    public Object getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(Object frontDefault) {
        this.frontDefault = frontDefault;
    }

    public Object getFrontGray() {
        return frontGray;
    }

    public void setFrontGray(Object frontGray) {
        this.frontGray = frontGray;
    }

    public Object getFrontTransparent() {
        return frontTransparent;
    }

    public void setFrontTransparent(Object frontTransparent) {
        this.frontTransparent = frontTransparent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RedBlue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("backDefault");
        sb.append('=');
        sb.append(((this.backDefault == null)?"<null>":this.backDefault));
        sb.append(',');
        sb.append("backGray");
        sb.append('=');
        sb.append(((this.backGray == null)?"<null>":this.backGray));
        sb.append(',');
        sb.append("backTransparent");
        sb.append('=');
        sb.append(((this.backTransparent == null)?"<null>":this.backTransparent));
        sb.append(',');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
        sb.append(',');
        sb.append("frontGray");
        sb.append('=');
        sb.append(((this.frontGray == null)?"<null>":this.frontGray));
        sb.append(',');
        sb.append("frontTransparent");
        sb.append('=');
        sb.append(((this.frontTransparent == null)?"<null>":this.frontTransparent));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.backTransparent == null)? 0 :this.backTransparent.hashCode()));
        result = ((result* 31)+((this.frontTransparent == null)? 0 :this.frontTransparent.hashCode()));
        result = ((result* 31)+((this.backDefault == null)? 0 :this.backDefault.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        result = ((result* 31)+((this.frontGray == null)? 0 :this.frontGray.hashCode()));
        result = ((result* 31)+((this.backGray == null)? 0 :this.backGray.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RedBlue) == false) {
            return false;
        }
        RedBlue rhs = ((RedBlue) other);
        return (((((((this.backTransparent == rhs.backTransparent)||((this.backTransparent!= null)&&this.backTransparent.equals(rhs.backTransparent)))&&((this.frontTransparent == rhs.frontTransparent)||((this.frontTransparent!= null)&&this.frontTransparent.equals(rhs.frontTransparent))))&&((this.backDefault == rhs.backDefault)||((this.backDefault!= null)&&this.backDefault.equals(rhs.backDefault))))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.frontGray == rhs.frontGray)||((this.frontGray!= null)&&this.frontGray.equals(rhs.frontGray))))&&((this.backGray == rhs.backGray)||((this.backGray!= null)&&this.backGray.equals(rhs.backGray))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(backDefault);
        dest.writeValue(backGray);
        dest.writeValue(backTransparent);
        dest.writeValue(frontDefault);
        dest.writeValue(frontGray);
        dest.writeValue(frontTransparent);
    }

    public int describeContents() {
        return  0;
    }

}
