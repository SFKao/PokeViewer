
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Animated implements Serializable, Parcelable
{

    @SerializedName("back_default")
    @Expose
    private Object backDefault;
    @SerializedName("back_female")
    @Expose
    private Object backFemale;
    @SerializedName("back_shiny")
    @Expose
    private Object backShiny;
    @SerializedName("back_shiny_female")
    @Expose
    private Object backShinyFemale;
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
    public final static Creator<Animated> CREATOR = new Creator<Animated>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Animated createFromParcel(android.os.Parcel in) {
            return new Animated(in);
        }

        public Animated[] newArray(int size) {
            return (new Animated[size]);
        }

    }
    ;
    private final static long serialVersionUID = -200358899978391943L;

    protected Animated(android.os.Parcel in) {
        this.backDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backFemale = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backShiny = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backShinyFemale = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontFemale = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShiny = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShinyFemale = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Animated() {
    }

    public Object getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(Object backDefault) {
        this.backDefault = backDefault;
    }

    public Object getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(Object backFemale) {
        this.backFemale = backFemale;
    }

    public Object getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(Object backShiny) {
        this.backShiny = backShiny;
    }

    public Object getBackShinyFemale() {
        return backShinyFemale;
    }

    public void setBackShinyFemale(Object backShinyFemale) {
        this.backShinyFemale = backShinyFemale;
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
        sb.append(Animated.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("backDefault");
        sb.append('=');
        sb.append(((this.backDefault == null)?"<null>":this.backDefault));
        sb.append(',');
        sb.append("backFemale");
        sb.append('=');
        sb.append(((this.backFemale == null)?"<null>":this.backFemale));
        sb.append(',');
        sb.append("backShiny");
        sb.append('=');
        sb.append(((this.backShiny == null)?"<null>":this.backShiny));
        sb.append(',');
        sb.append("backShinyFemale");
        sb.append('=');
        sb.append(((this.backShinyFemale == null)?"<null>":this.backShinyFemale));
        sb.append(',');
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
        result = ((result* 31)+((this.backFemale == null)? 0 :this.backFemale.hashCode()));
        result = ((result* 31)+((this.frontShiny == null)? 0 :this.frontShiny.hashCode()));
        result = ((result* 31)+((this.backDefault == null)? 0 :this.backDefault.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        result = ((result* 31)+((this.frontFemale == null)? 0 :this.frontFemale.hashCode()));
        result = ((result* 31)+((this.backShinyFemale == null)? 0 :this.backShinyFemale.hashCode()));
        result = ((result* 31)+((this.backShiny == null)? 0 :this.backShiny.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Animated) == false) {
            return false;
        }
        Animated rhs = ((Animated) other);
        return (((((((((this.frontShinyFemale == rhs.frontShinyFemale)||((this.frontShinyFemale!= null)&&this.frontShinyFemale.equals(rhs.frontShinyFemale)))&&((this.backFemale == rhs.backFemale)||((this.backFemale!= null)&&this.backFemale.equals(rhs.backFemale))))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))))&&((this.backDefault == rhs.backDefault)||((this.backDefault!= null)&&this.backDefault.equals(rhs.backDefault))))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.frontFemale == rhs.frontFemale)||((this.frontFemale!= null)&&this.frontFemale.equals(rhs.frontFemale))))&&((this.backShinyFemale == rhs.backShinyFemale)||((this.backShinyFemale!= null)&&this.backShinyFemale.equals(rhs.backShinyFemale))))&&((this.backShiny == rhs.backShiny)||((this.backShiny!= null)&&this.backShiny.equals(rhs.backShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(backDefault);
        dest.writeValue(backFemale);
        dest.writeValue(backShiny);
        dest.writeValue(backShinyFemale);
        dest.writeValue(frontDefault);
        dest.writeValue(frontFemale);
        dest.writeValue(frontShiny);
        dest.writeValue(frontShinyFemale);
    }

    public int describeContents() {
        return  0;
    }

}
