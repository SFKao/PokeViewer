
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Crystal implements Serializable, Parcelable
{

    @SerializedName("back_default")
    @Expose
    private Object backDefault;
    @SerializedName("back_shiny")
    @Expose
    private Object backShiny;
    @SerializedName("back_shiny_transparent")
    @Expose
    private Object backShinyTransparent;
    @SerializedName("back_transparent")
    @Expose
    private Object backTransparent;
    @SerializedName("front_default")
    @Expose
    private Object frontDefault;
    @SerializedName("front_shiny")
    @Expose
    private Object frontShiny;
    @SerializedName("front_shiny_transparent")
    @Expose
    private Object frontShinyTransparent;
    @SerializedName("front_transparent")
    @Expose
    private Object frontTransparent;
    public final static Creator<Crystal> CREATOR = new Creator<Crystal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Crystal createFromParcel(android.os.Parcel in) {
            return new Crystal(in);
        }

        public Crystal[] newArray(int size) {
            return (new Crystal[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7092363349758359255L;

    protected Crystal(android.os.Parcel in) {
        this.backDefault = in.readValue((Object.class.getClassLoader()));
        this.backShiny = in.readValue((Object.class.getClassLoader()));
        this.backShinyTransparent = in.readValue((Object.class.getClassLoader()));
        this.backTransparent = in.readValue((Object.class.getClassLoader()));
        this.frontDefault = in.readValue((Object.class.getClassLoader()));
        this.frontShiny = in.readValue((Object.class.getClassLoader()));
        this.frontShinyTransparent = in.readValue((Object.class.getClassLoader()));
        this.frontTransparent = in.readValue((Object.class.getClassLoader()));
    }

    public Crystal() {
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

    public Object getBackShinyTransparent() {
        return backShinyTransparent;
    }

    public void setBackShinyTransparent(Object backShinyTransparent) {
        this.backShinyTransparent = backShinyTransparent;
    }

    public Object getBackTransparent() {
        return backTransparent;
    }

    public void setBackTransparent(Object backTransparent) {
        this.backTransparent = backTransparent;
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

    public Object getFrontShinyTransparent() {
        return frontShinyTransparent;
    }

    public void setFrontShinyTransparent(Object frontShinyTransparent) {
        this.frontShinyTransparent = frontShinyTransparent;
    }

    public Object getFrontTransparent() {
        return frontTransparent;
    }

    public void setFrontTransparent(Object frontTransparent) {
        this.frontTransparent = frontTransparent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Crystal.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("backDefault");
        sb.append('=');
        sb.append(((this.backDefault == null)?"<null>":this.backDefault));
        sb.append(',');
        sb.append("backShiny");
        sb.append('=');
        sb.append(((this.backShiny == null)?"<null>":this.backShiny));
        sb.append(',');
        sb.append("backShinyTransparent");
        sb.append('=');
        sb.append(((this.backShinyTransparent == null)?"<null>":this.backShinyTransparent));
        sb.append(',');
        sb.append("backTransparent");
        sb.append('=');
        sb.append(((this.backTransparent == null)?"<null>":this.backTransparent));
        sb.append(',');
        sb.append("frontDefault");
        sb.append('=');
        sb.append(((this.frontDefault == null)?"<null>":this.frontDefault));
        sb.append(',');
        sb.append("frontShiny");
        sb.append('=');
        sb.append(((this.frontShiny == null)?"<null>":this.frontShiny));
        sb.append(',');
        sb.append("frontShinyTransparent");
        sb.append('=');
        sb.append(((this.frontShinyTransparent == null)?"<null>":this.frontShinyTransparent));
        sb.append(',');
        sb.append("frontTransparent");
        sb.append('=');
        sb.append(((this.frontTransparent == null)?"<null>":this.frontTransparent));
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
        result = ((result* 31)+((this.backTransparent == null)? 0 :this.backTransparent.hashCode()));
        result = ((result* 31)+((this.frontTransparent == null)? 0 :this.frontTransparent.hashCode()));
        result = ((result* 31)+((this.backShinyTransparent == null)? 0 :this.backShinyTransparent.hashCode()));
        result = ((result* 31)+((this.frontShiny == null)? 0 :this.frontShiny.hashCode()));
        result = ((result* 31)+((this.frontShinyTransparent == null)? 0 :this.frontShinyTransparent.hashCode()));
        result = ((result* 31)+((this.backDefault == null)? 0 :this.backDefault.hashCode()));
        result = ((result* 31)+((this.frontDefault == null)? 0 :this.frontDefault.hashCode()));
        result = ((result* 31)+((this.backShiny == null)? 0 :this.backShiny.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Crystal) == false) {
            return false;
        }
        Crystal rhs = ((Crystal) other);
        return (((((((((this.backTransparent == rhs.backTransparent)||((this.backTransparent!= null)&&this.backTransparent.equals(rhs.backTransparent)))&&((this.frontTransparent == rhs.frontTransparent)||((this.frontTransparent!= null)&&this.frontTransparent.equals(rhs.frontTransparent))))&&((this.backShinyTransparent == rhs.backShinyTransparent)||((this.backShinyTransparent!= null)&&this.backShinyTransparent.equals(rhs.backShinyTransparent))))&&((this.frontShiny == rhs.frontShiny)||((this.frontShiny!= null)&&this.frontShiny.equals(rhs.frontShiny))))&&((this.frontShinyTransparent == rhs.frontShinyTransparent)||((this.frontShinyTransparent!= null)&&this.frontShinyTransparent.equals(rhs.frontShinyTransparent))))&&((this.backDefault == rhs.backDefault)||((this.backDefault!= null)&&this.backDefault.equals(rhs.backDefault))))&&((this.frontDefault == rhs.frontDefault)||((this.frontDefault!= null)&&this.frontDefault.equals(rhs.frontDefault))))&&((this.backShiny == rhs.backShiny)||((this.backShiny!= null)&&this.backShiny.equals(rhs.backShiny))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(backDefault);
        dest.writeValue(backShiny);
        dest.writeValue(backShinyTransparent);
        dest.writeValue(backTransparent);
        dest.writeValue(frontDefault);
        dest.writeValue(frontShiny);
        dest.writeValue(frontShinyTransparent);
        dest.writeValue(frontTransparent);
    }

    public int describeContents() {
        return  0;
    }

}
