
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FireredLeafgreen implements Serializable, Parcelable
{

    @SerializedName("back_default")
    @Expose
    private Object backDefault;
    @SerializedName("back_shiny")
    @Expose
    private Object backShiny;
    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_shiny")
    @Expose
    private Object frontShiny;
    public final static Creator<FireredLeafgreen> CREATOR = new Creator<FireredLeafgreen>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FireredLeafgreen createFromParcel(android.os.Parcel in) {
            return new FireredLeafgreen(in);
        }

        public FireredLeafgreen[] newArray(int size) {
            return (new FireredLeafgreen[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7065384397827026967L;

    protected FireredLeafgreen(android.os.Parcel in) {
        this.backDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.backShiny = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontDefault = ((Object) in.readValue((Object.class.getClassLoader())));
        this.frontShiny = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public FireredLeafgreen() {
    }

    public Object getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(Object backDefault) {
        this.backDefault = backDefault;
    }

    public Object getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(Object backShiny) {
        this.backShiny = backShiny;
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
        sb.append(FireredLeafgreen.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("backDefault");
        sb.append('=');
        sb.append(((this.backDefault == null)?"<null>":this.backDefault));
        sb.append(',');
        sb.append("backShiny");
        sb.append('=');
        sb.append(((this.backShiny == null)?"<null>":this.backShiny));
        sb.append(',');
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
        result = ((result* 31)+((this.backDefault == null)? 0 :this.backDefault.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        result = ((result* 31)+((this.backShiny == null)? 0 :this.backShiny.hashCode()));
        result = ((result* 31)+((this.frontShiny == null)? 0 :this.frontShiny.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FireredLeafgreen) == false) {
            return false;
        }
        FireredLeafgreen rhs = ((FireredLeafgreen) other);
        return (((((this.backDefault == rhs.backDefault)||((this.backDefault!= null)&&this.backDefault.equals(rhs.backDefault)))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.backShiny == rhs.backShiny)||((this.backShiny!= null)&&this.backShiny.equals(rhs.backShiny))))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(backDefault);
        dest.writeValue(backShiny);
        dest.writeValue(frontDefault);
        dest.writeValue(frontShiny);
    }

    public int describeContents() {
        return  0;
    }

}
