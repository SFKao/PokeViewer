
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Icons__1 implements Serializable, Parcelable
{

    @SerializedName("front_default")
    @Expose
    private String frontDefault;
    @SerializedName("front_female")
    @Expose
    private Object frontFemale;
    public final static Creator<Icons__1> CREATOR = new Creator<Icons__1>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Icons__1 createFromParcel(android.os.Parcel in) {
            return new Icons__1(in);
        }

        public Icons__1 [] newArray(int size) {
            return (new Icons__1[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4205351874129028118L;

    protected Icons__1(android.os.Parcel in) {
        this.frontDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.frontFemale = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Icons__1() {
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
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
        sb.append(Icons__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof Icons__1) == false) {
            return false;
        }
        Icons__1 rhs = ((Icons__1) other);
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
