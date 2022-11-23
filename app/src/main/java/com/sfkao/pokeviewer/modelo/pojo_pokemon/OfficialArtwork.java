
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OfficialArtwork implements Serializable, Parcelable
{

    @SerializedName("front_default")
    @Expose
    private String frontDefault;
    public final static Creator<OfficialArtwork> CREATOR = new Creator<OfficialArtwork>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OfficialArtwork createFromParcel(android.os.Parcel in) {
            return new OfficialArtwork(in);
        }

        public OfficialArtwork[] newArray(int size) {
            return (new OfficialArtwork[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1655014817575402611L;

    protected OfficialArtwork(android.os.Parcel in) {
        this.frontDefault = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OfficialArtwork() {
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OfficialArtwork.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
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
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OfficialArtwork) == false) {
            return false;
        }
        OfficialArtwork rhs = ((OfficialArtwork) other);
        return ((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(frontDefault);
    }

    public int describeContents() {
        return  0;
    }

}
