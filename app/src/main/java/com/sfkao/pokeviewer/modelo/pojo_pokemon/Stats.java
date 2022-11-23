
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Stats implements Serializable, Parcelable
{

    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;
    @SerializedName("effort")
    @Expose
    private Integer effort;
    @SerializedName("stat")
    @Expose
    private Stat stat;
    public final static Creator<Stats> CREATOR = new Creator<Stats>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Stats createFromParcel(android.os.Parcel in) {
            return new Stats(in);
        }

        public Stats[] newArray(int size) {
            return (new Stats[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6511520510343891462L;

    protected Stats(android.os.Parcel in) {
        this.baseStat = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.effort = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stat = ((Stat) in.readValue((Stat.class.getClassLoader())));
    }

    public Stats() {
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Stats.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("baseStat");
        sb.append('=');
        sb.append(((this.baseStat == null)?"<null>":this.baseStat));
        sb.append(',');
        sb.append("effort");
        sb.append('=');
        sb.append(((this.effort == null)?"<null>":this.effort));
        sb.append(',');
        sb.append("stat");
        sb.append('=');
        sb.append(((this.stat == null)?"<null>":this.stat));
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
        result = ((result* 31)+((this.effort == null)? 0 :this.effort.hashCode()));
        result = ((result* 31)+((this.stat == null)? 0 :this.stat.hashCode()));
        result = ((result* 31)+((this.baseStat == null)? 0 :this.baseStat.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stats) == false) {
            return false;
        }
        Stats rhs = ((Stats) other);
        return ((((this.effort == rhs.effort)||((this.effort!= null)&&this.effort.equals(rhs.effort)))&&((this.stat == rhs.stat)||((this.stat!= null)&&this.stat.equals(rhs.stat))))&&((this.baseStat == rhs.baseStat)||((this.baseStat!= null)&&this.baseStat.equals(rhs.baseStat))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(baseStat);
        dest.writeValue(effort);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return  0;
    }

}
