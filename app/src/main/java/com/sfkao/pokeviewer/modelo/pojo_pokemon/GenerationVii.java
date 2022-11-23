
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationVii implements Serializable, Parcelable
{

    @SerializedName("icons")
    @Expose
    private Icons icons;
    @SerializedName("ultra-sun-ultra-moon")
    @Expose
    private UltraSunUltraMoon ultraSunUltraMoon;
    public final static Creator<GenerationVii> CREATOR = new Creator<GenerationVii>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationVii createFromParcel(android.os.Parcel in) {
            return new GenerationVii(in);
        }

        public GenerationVii[] newArray(int size) {
            return (new GenerationVii[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1708447258006575407L;

    protected GenerationVii(android.os.Parcel in) {
        this.icons = ((Icons) in.readValue((Icons.class.getClassLoader())));
        this.ultraSunUltraMoon = ((UltraSunUltraMoon) in.readValue((UltraSunUltraMoon.class.getClassLoader())));
    }

    public GenerationVii() {
    }

    public Icons getIcons() {
        return icons;
    }

    public void setIcons(Icons icons) {
        this.icons = icons;
    }

    public UltraSunUltraMoon getUltraSunUltraMoon() {
        return ultraSunUltraMoon;
    }

    public void setUltraSunUltraMoon(UltraSunUltraMoon ultraSunUltraMoon) {
        this.ultraSunUltraMoon = ultraSunUltraMoon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationVii.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("icons");
        sb.append('=');
        sb.append(((this.icons == null)?"<null>":this.icons));
        sb.append(',');
        sb.append("ultraSunUltraMoon");
        sb.append('=');
        sb.append(((this.ultraSunUltraMoon == null)?"<null>":this.ultraSunUltraMoon));
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
        result = ((result* 31)+((this.ultraSunUltraMoon == null)? 0 :this.ultraSunUltraMoon.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationVii) == false) {
            return false;
        }
        GenerationVii rhs = ((GenerationVii) other);
        return (((this.icons == rhs.icons)||((this.icons!= null)&&this.icons.equals(rhs.icons)))&&((this.ultraSunUltraMoon == rhs.ultraSunUltraMoon)||((this.ultraSunUltraMoon!= null)&&this.ultraSunUltraMoon.equals(rhs.ultraSunUltraMoon))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(icons);
        dest.writeValue(ultraSunUltraMoon);
    }

    public int describeContents() {
        return  0;
    }

}
