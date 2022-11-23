
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationIv implements Serializable, Parcelable
{

    @SerializedName("diamond-pearl")
    @Expose
    private DiamondPearl diamondPearl;
    @SerializedName("heartgold-soulsilver")
    @Expose
    private HeartgoldSoulsilver heartgoldSoulsilver;
    @SerializedName("platinum")
    @Expose
    private Platinum platinum;
    public final static Creator<GenerationIv> CREATOR = new Creator<GenerationIv>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationIv createFromParcel(android.os.Parcel in) {
            return new GenerationIv(in);
        }

        public GenerationIv[] newArray(int size) {
            return (new GenerationIv[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1275391173454632021L;

    protected GenerationIv(android.os.Parcel in) {
        this.diamondPearl = ((DiamondPearl) in.readValue((DiamondPearl.class.getClassLoader())));
        this.heartgoldSoulsilver = ((HeartgoldSoulsilver) in.readValue((HeartgoldSoulsilver.class.getClassLoader())));
        this.platinum = ((Platinum) in.readValue((Platinum.class.getClassLoader())));
    }

    public GenerationIv() {
    }

    public DiamondPearl getDiamondPearl() {
        return diamondPearl;
    }

    public void setDiamondPearl(DiamondPearl diamondPearl) {
        this.diamondPearl = diamondPearl;
    }

    public HeartgoldSoulsilver getHeartgoldSoulsilver() {
        return heartgoldSoulsilver;
    }

    public void setHeartgoldSoulsilver(HeartgoldSoulsilver heartgoldSoulsilver) {
        this.heartgoldSoulsilver = heartgoldSoulsilver;
    }

    public Platinum getPlatinum() {
        return platinum;
    }

    public void setPlatinum(Platinum platinum) {
        this.platinum = platinum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationIv.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("diamondPearl");
        sb.append('=');
        sb.append(((this.diamondPearl == null)?"<null>":this.diamondPearl));
        sb.append(',');
        sb.append("heartgoldSoulsilver");
        sb.append('=');
        sb.append(((this.heartgoldSoulsilver == null)?"<null>":this.heartgoldSoulsilver));
        sb.append(',');
        sb.append("platinum");
        sb.append('=');
        sb.append(((this.platinum == null)?"<null>":this.platinum));
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
        result = ((result* 31)+((this.platinum == null)? 0 :this.platinum.hashCode()));
        result = ((result* 31)+((this.heartgoldSoulsilver == null)? 0 :this.heartgoldSoulsilver.hashCode()));
        result = ((result* 31)+((this.diamondPearl == null)? 0 :this.diamondPearl.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationIv) == false) {
            return false;
        }
        GenerationIv rhs = ((GenerationIv) other);
        return ((((this.platinum == rhs.platinum)||((this.platinum!= null)&&this.platinum.equals(rhs.platinum)))&&((this.heartgoldSoulsilver == rhs.heartgoldSoulsilver)||((this.heartgoldSoulsilver!= null)&&this.heartgoldSoulsilver.equals(rhs.heartgoldSoulsilver))))&&((this.diamondPearl == rhs.diamondPearl)||((this.diamondPearl!= null)&&this.diamondPearl.equals(rhs.diamondPearl))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(diamondPearl);
        dest.writeValue(heartgoldSoulsilver);
        dest.writeValue(platinum);
    }

    public int describeContents() {
        return  0;
    }

}
