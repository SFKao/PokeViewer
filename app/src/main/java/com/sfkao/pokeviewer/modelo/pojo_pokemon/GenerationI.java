
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationI implements Serializable, Parcelable
{

    @SerializedName("red-blue")
    @Expose
    private RedBlue redBlue;
    @SerializedName("yellow")
    @Expose
    private Yellow yellow;
    public final static Creator<GenerationI> CREATOR = new Creator<GenerationI>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationI createFromParcel(android.os.Parcel in) {
            return new GenerationI(in);
        }

        public GenerationI[] newArray(int size) {
            return (new GenerationI[size]);
        }

    }
    ;
    private final static long serialVersionUID = -960901326512563710L;

    protected GenerationI(android.os.Parcel in) {
        this.redBlue = ((RedBlue) in.readValue((RedBlue.class.getClassLoader())));
        this.yellow = ((Yellow) in.readValue((Yellow.class.getClassLoader())));
    }

    public GenerationI() {
    }

    public RedBlue getRedBlue() {
        return redBlue;
    }

    public void setRedBlue(RedBlue redBlue) {
        this.redBlue = redBlue;
    }

    public Yellow getYellow() {
        return yellow;
    }

    public void setYellow(Yellow yellow) {
        this.yellow = yellow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationI.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("redBlue");
        sb.append('=');
        sb.append(((this.redBlue == null)?"<null>":this.redBlue));
        sb.append(',');
        sb.append("yellow");
        sb.append('=');
        sb.append(((this.yellow == null)?"<null>":this.yellow));
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
        result = ((result* 31)+((this.yellow == null)? 0 :this.yellow.hashCode()));
        result = ((result* 31)+((this.redBlue == null)? 0 :this.redBlue.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationI) == false) {
            return false;
        }
        GenerationI rhs = ((GenerationI) other);
        return (((this.yellow == rhs.yellow)||((this.yellow!= null)&&this.yellow.equals(rhs.yellow)))&&((this.redBlue == rhs.redBlue)||((this.redBlue!= null)&&this.redBlue.equals(rhs.redBlue))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(redBlue);
        dest.writeValue(yellow);
    }

    public int describeContents() {
        return  0;
    }

}
