
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Emerald implements Serializable, Parcelable
{

    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_shiny")
    @Expose
    private Object frontShiny;
    public final static Creator<Emerald> CREATOR = new Creator<Emerald>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Emerald createFromParcel(android.os.Parcel in) {
            return new Emerald(in);
        }

        public Emerald[] newArray(int size) {
            return (new Emerald[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3109704206056881486L;

    protected Emerald(android.os.Parcel in) {
        this.frontDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShiny = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Emerald() {
    }

    public Object getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(Object frontDefault) {
        this.frontDefault = frontDefault;
    }

    public Object getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(Object frontShiny) {
        this.frontShiny = frontShiny;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Emerald.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
        sb.append(',');
        sb.append("frontShiny");
        sb.append('=');
        sb.append(((this.frontShiny == null)?"<null>":this.frontShiny));
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
        result = ((result* 31)+((this.frontShiny == null)? 0 :this.frontShiny.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Emerald) == false) {
            return false;
        }
        Emerald rhs = ((Emerald) other);
        return (((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault)))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(frontDefault);
        dest.writeValue(frontShiny);
    }

    public int describeContents() {
        return  0;
    }

}
