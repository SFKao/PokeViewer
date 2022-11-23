
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationViii implements Serializable, Parcelable
{

    @SerializedName("icons")
    @Expose
    private Icons__1 icons;
    public final static Creator<GenerationViii> CREATOR = new Creator<GenerationViii>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationViii createFromParcel(android.os.Parcel in) {
            return new GenerationViii(in);
        }

        public GenerationViii[] newArray(int size) {
            return (new GenerationViii[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4182107924934502837L;

    protected GenerationViii(android.os.Parcel in) {
        this.icons = ((Icons__1) in.readValue((Icons__1.class.getClassLoader())));
    }

    public GenerationViii() {
    }

    public Icons__1 getIcons() {
        return icons;
    }

    public void setIcons(Icons__1 icons) {
        this.icons = icons;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationViii.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("icons");
        sb.append('=');
        sb.append(((this.icons == null)?"<null>":this.icons));
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
        result = ((result* 31)+((this.icons == null)? 0 :this.icons.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationViii) == false) {
            return false;
        }
        GenerationViii rhs = ((GenerationViii) other);
        return ((this.icons == rhs.icons)||((this.icons!= null)&&this.icons.equals(rhs.icons)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(icons);
    }

    public int describeContents() {
        return  0;
    }

}
