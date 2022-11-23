
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationIi implements Serializable, Parcelable
{

    @SerializedName("crystal")
    @Expose
    private Crystal crystal;
    @SerializedName("gold")
    @Expose
    private Gold gold;
    @SerializedName("silver")
    @Expose
    private Silver silver;
    public final static Creator<GenerationIi> CREATOR = new Creator<GenerationIi>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationIi createFromParcel(android.os.Parcel in) {
            return new GenerationIi(in);
        }

        public GenerationIi[] newArray(int size) {
            return (new GenerationIi[size]);
        }

    }
    ;
    private final static long serialVersionUID = -9180718249274105100L;

    protected GenerationIi(android.os.Parcel in) {
        this.crystal = ((Crystal) in.readValue((Crystal.class.getClassLoader())));
        this.gold = ((Gold) in.readValue((Gold.class.getClassLoader())));
        this.silver = ((Silver) in.readValue((Silver.class.getClassLoader())));
    }

    public GenerationIi() {
    }

    public Crystal getCrystal() {
        return crystal;
    }

    public void setCrystal(Crystal crystal) {
        this.crystal = crystal;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public Silver getSilver() {
        return silver;
    }

    public void setSilver(Silver silver) {
        this.silver = silver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationIi.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("crystal");
        sb.append('=');
        sb.append(((this.crystal == null)?"<null>":this.crystal));
        sb.append(',');
        sb.append("gold");
        sb.append('=');
        sb.append(((this.gold == null)?"<null>":this.gold));
        sb.append(',');
        sb.append("silver");
        sb.append('=');
        sb.append(((this.silver == null)?"<null>":this.silver));
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
        result = ((result* 31)+((this.gold == null)? 0 :this.gold.hashCode()));
        result = ((result* 31)+((this.silver == null)? 0 :this.silver.hashCode()));
        result = ((result* 31)+((this.crystal == null)? 0 :this.crystal.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationIi) == false) {
            return false;
        }
        GenerationIi rhs = ((GenerationIi) other);
        return ((((this.gold == rhs.gold)||((this.gold!= null)&&this.gold.equals(rhs.gold)))&&((this.silver == rhs.silver)||((this.silver!= null)&&this.silver.equals(rhs.silver))))&&((this.crystal == rhs.crystal)||((this.crystal!= null)&&this.crystal.equals(rhs.crystal))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(crystal);
        dest.writeValue(gold);
        dest.writeValue(silver);
    }

    public int describeContents() {
        return  0;
    }

}
