
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationIii implements Serializable, Parcelable
{

    @SerializedName("emerald")
    @Expose
    private Emerald emerald;
    @SerializedName("firered-leafgreen")
    @Expose
    private FireredLeafgreen fireredLeafgreen;
    @SerializedName("ruby-sapphire")
    @Expose
    private RubySapphire rubySapphire;
    public final static Creator<GenerationIii> CREATOR = new Creator<GenerationIii>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationIii createFromParcel(android.os.Parcel in) {
            return new GenerationIii(in);
        }

        public GenerationIii[] newArray(int size) {
            return (new GenerationIii[size]);
        }

    }
    ;
    private final static long serialVersionUID = -664673104044337322L;

    protected GenerationIii(android.os.Parcel in) {
        this.emerald = ((Emerald) in.readValue((Emerald.class.getClassLoader())));
        this.fireredLeafgreen = ((FireredLeafgreen) in.readValue((FireredLeafgreen.class.getClassLoader())));
        this.rubySapphire = ((RubySapphire) in.readValue((RubySapphire.class.getClassLoader())));
    }

    public GenerationIii() {
    }

    public Emerald getEmerald() {
        return emerald;
    }

    public void setEmerald(Emerald emerald) {
        this.emerald = emerald;
    }

    public FireredLeafgreen getFireredLeafgreen() {
        return fireredLeafgreen;
    }

    public void setFireredLeafgreen(FireredLeafgreen fireredLeafgreen) {
        this.fireredLeafgreen = fireredLeafgreen;
    }

    public RubySapphire getRubySapphire() {
        return rubySapphire;
    }

    public void setRubySapphire(RubySapphire rubySapphire) {
        this.rubySapphire = rubySapphire;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationIii.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("emerald");
        sb.append('=');
        sb.append(((this.emerald == null)?"<null>":this.emerald));
        sb.append(',');
        sb.append("fireredLeafgreen");
        sb.append('=');
        sb.append(((this.fireredLeafgreen == null)?"<null>":this.fireredLeafgreen));
        sb.append(',');
        sb.append("rubySapphire");
        sb.append('=');
        sb.append(((this.rubySapphire == null)?"<null>":this.rubySapphire));
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
        result = ((result* 31)+((this.fireredLeafgreen == null)? 0 :this.fireredLeafgreen.hashCode()));
        result = ((result* 31)+((this.rubySapphire == null)? 0 :this.rubySapphire.hashCode()));
        result = ((result* 31)+((this.emerald == null)? 0 :this.emerald.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationIii) == false) {
            return false;
        }
        GenerationIii rhs = ((GenerationIii) other);
        return ((((this.fireredLeafgreen == rhs.fireredLeafgreen)||((this.fireredLeafgreen!= null)&&this.fireredLeafgreen.equals(rhs.fireredLeafgreen)))&&((this.rubySapphire == rhs.rubySapphire)||((this.rubySapphire!= null)&&this.rubySapphire.equals(rhs.rubySapphire))))&&((this.emerald == rhs.emerald)||((this.emerald!= null)&&this.emerald.equals(rhs.emerald))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(emerald);
        dest.writeValue(fireredLeafgreen);
        dest.writeValue(rubySapphire);
    }

    public int describeContents() {
        return  0;
    }

}
