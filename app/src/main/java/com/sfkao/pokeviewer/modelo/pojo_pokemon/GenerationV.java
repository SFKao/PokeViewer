
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationV implements Serializable, Parcelable
{

    @SerializedName("black-white")
    @Expose
    private BlackWhite blackWhite;
    public final static Creator<GenerationV> CREATOR = new Creator<GenerationV>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationV createFromParcel(android.os.Parcel in) {
            return new GenerationV(in);
        }

        public GenerationV[] newArray(int size) {
            return (new GenerationV[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3475355679688836314L;

    protected GenerationV(android.os.Parcel in) {
        this.blackWhite = ((BlackWhite) in.readValue((BlackWhite.class.getClassLoader())));
    }

    public GenerationV() {
    }

    public BlackWhite getBlackWhite() {
        return blackWhite;
    }

    public void setBlackWhite(BlackWhite blackWhite) {
        this.blackWhite = blackWhite;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationV.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("blackWhite");
        sb.append('=');
        sb.append(((this.blackWhite == null)?"<null>":this.blackWhite));
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
        result = ((result* 31)+((this.blackWhite == null)? 0 :this.blackWhite.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationV) == false) {
            return false;
        }
        GenerationV rhs = ((GenerationV) other);
        return ((this.blackWhite == rhs.blackWhite)||((this.blackWhite!= null)&&this.blackWhite.equals(rhs.blackWhite)));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(blackWhite);
    }

    public int describeContents() {
        return  0;
    }

}
