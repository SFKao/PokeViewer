
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BlackWhite implements Serializable, Parcelable
{

    @SerializedName("animated")
    @Expose
    private Animated animated;
    @SerializedName("back_default")
    @Expose
    private String backDefault;
    @SerializedName("back_female")
    @Expose
    private Object backFemale;
    @SerializedName("back_shiny")
    @Expose
    private String backShiny;
    @SerializedName("back_shiny_female")
    @Expose
    private Object backShinyFemale;
    @SerializedName("front_default")
    @Expose
    private String frontDefault;
    @SerializedName("front_female")
    @Expose
    private Object frontFemale;
    @SerializedName("front_shiny")
    @Expose
    private String frontShiny;
    @SerializedName("front_shiny_female")
    @Expose
    private Object frontShinyFemale;
    public final static Creator<BlackWhite> CREATOR = new Creator<BlackWhite>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BlackWhite createFromParcel(android.os.Parcel in) {
            return new BlackWhite(in);
        }

        public BlackWhite[] newArray(int size) {
            return (new BlackWhite[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1488265503225613519L;

    protected BlackWhite(android.os.Parcel in) {
        this.animated = ((Animated) in.readValue((Animated.class.getClassLoader())));
        this.backDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.backFemale = in.readValue((Object.class.getClassLoader()));
        this.backShiny = ((String) in.readValue((String.class.getClassLoader())));
        this.backShinyFemale = in.readValue((Object.class.getClassLoader()));
        this.frontDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.frontFemale = in.readValue((Object.class.getClassLoader()));
        this.frontShiny = ((String) in.readValue((String.class.getClassLoader())));
        this.frontShinyFemale = in.readValue((Object.class.getClassLoader()));
    }

    public BlackWhite() {
    }

    public Animated getAnimated() {
        return animated;
    }

    public void setAnimated(Animated animated) {
        this.animated = animated;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public Object getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(Object backFemale) {
        this.backFemale = backFemale;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public Object getBackShinyFemale() {
        return backShinyFemale;
    }

    public void setBackShinyFemale(Object backShinyFemale) {
        this.backShinyFemale = backShinyFemale;
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

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
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
        sb.append(BlackWhite.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("animated");
        sb.append('=');
        sb.append(((this.animated == null)?"<null>":this.animated));
        sb.append(',');
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
        result = ((result* 31)+((this.animated == null)? 0 :this.animated.hashCode()));
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
        if ((other instanceof BlackWhite) == false) {
            return false;
        }
        BlackWhite rhs = ((BlackWhite) other);
        return ((((((((((this.frontShinyFemale == rhs.frontShinyFemale)||((this.frontShinyFemale!= null)&&this.frontShinyFemale.equals(rhs.frontShinyFemale)))&&((this.backFemale == rhs.backFemale)||((this.backFemale!= null)&&this.backFemale.equals(rhs.backFemale))))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))))&&((this.animated == rhs.animated)||((this.animated!= null)&&this.animated.equals(rhs.animated))))&&((this.backDefault == rhs.backDefault)||((this.backDefault!= null)&&this.backDefault.equals(rhs.backDefault))))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.frontFemale == rhs.frontFemale)||((this.frontFemale!= null)&&this.frontFemale.equals(rhs.frontFemale))))&&((this.backShinyFemale == rhs.backShinyFemale)||((this.backShinyFemale!= null)&&this.backShinyFemale.equals(rhs.backShinyFemale))))&&((this.backShiny == rhs.backShiny)||((this.backShiny!= null)&&this.backShiny.equals(rhs.backShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(animated);
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
