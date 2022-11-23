
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class XY implements Serializable, Parcelable
{

    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_female")
    @Expose
    private Object frontFemale;
    @SerializedName("front_shiny")
    @Expose
    private Object frontShiny;
    @SerializedName("front_shiny_female")
    @Expose
    private Object frontShinyFemale;
    public final static Creator<XY> CREATOR = new Creator<XY>() {


        @SuppressWarnings({
            "unchecked"
        })
        public XY createFromParcel(android.os.Parcel in) {
            return new XY(in);
        }

        public XY[] newArray(int size) {
            return (new XY[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5636570276877559581L;

    protected XY(android.os.Parcel in) {
        this.frontDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontFemale = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShiny = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShinyFemale = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public XY() {
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

    public Object getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(Object frontShiny) {
        this.frontShiny = frontShiny;
    }

    public Object getFrontShinyFemale() {
        return frontShinyFemale;
    }

    public void setFrontShinyFemale(Object frontShinyFemale) {
        this.frontShinyFemale = frontShinyFemale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(XY.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
        sb.append(',');
        sb.append("frontFemale");
        sb.append('=');
        sb.append(((this.frontFemale == null)?"<null>":this.frontFemale));
        sb.append(',');
        sb.append("frontShiny");
        sb.append('=');
        sb.append(((this.frontShiny == null)?"<null>":this.frontShiny));
        sb.append(',');
        sb.append("frontShinyFemale");
        sb.append('=');
        sb.append(((this.frontShinyFemale == null)?"<null>":this.frontShinyFemale));
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
        result = ((result* 31)+((this.frontShinyFemale == null)? 0 :this.frontShinyFemale.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        result = ((result* 31)+((this.frontFemale == null)? 0 :this.frontFemale.hashCode()));
        result = ((result* 31)+((this.frontShiny == null)? 0 :this.frontShiny.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XY) == false) {
            return false;
        }
        XY rhs = ((XY) other);
        return (((((this.frontShinyFemale == rhs.frontShinyFemale)||((this.frontShinyFemale!= null)&&this.frontShinyFemale.equals(rhs.frontShinyFemale)))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.frontFemale == rhs.frontFemale)||((this.frontFemale!= null)&&this.frontFemale.equals(rhs.frontFemale))))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(frontDefault);
        dest.writeValue(frontFemale);
        dest.writeValue(frontShiny);
        dest.writeValue(frontShinyFemale);
    }

    public int describeContents() {
        return  0;
    }

}
