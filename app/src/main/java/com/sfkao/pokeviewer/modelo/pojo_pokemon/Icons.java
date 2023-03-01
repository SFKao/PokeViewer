
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Icons implements Serializable, Parcelable
{

    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_female")
    @Expose
    private Object frontFemale;
    public final static Creator<Icons> CREATOR = new Creator<Icons>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Icons createFromParcel(android.os.Parcel in) {
            return new Icons(in);
        }

        public Icons[] newArray(int size) {
            return (new Icons[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8430962881552581951L;

    protected Icons(android.os.Parcel in) {
        this.frontDefault = in.readValue((Object.class.getClassLoader()));
        this.frontFemale = in.readValue((Object.class.getClassLoader()));
    }

    public Icons() {
    }

    public Object getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(Object frontDefault) {
        this.frontDefault = frontDefault;
    }

    public Object getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(Object frontFemale) {
        this.frontFemale = frontFemale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Icons.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
        sb.append(',');
        sb.append("frontFemale");
        sb.append('=');
        sb.append(((this.frontFemale == null)?"<null>":this.frontFemale));
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
        result = ((result* 31)+((this.frontFemale == null)? 0 :this.frontFemale.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Icons) == false) {
            return false;
        }
        Icons rhs = ((Icons) other);
        return (((this.frontFemale == rhs.frontFemale)||((this.frontFemale!= null)&&this.frontFemale.equals(rhs.frontFemale)))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(frontDefault);
        dest.writeValue(frontFemale);
    }

    public int describeContents() {
        return  0;
    }

}
