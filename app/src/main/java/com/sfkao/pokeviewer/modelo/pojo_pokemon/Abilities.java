
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Abilities implements Serializable, Parcelable
{

    @SerializedName("ability")
    @Expose
    private Ability ability;
    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("slot")
    @Expose
    private Integer slot;
    public final static Creator<Abilities> CREATOR = new Creator<Abilities>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Abilities createFromParcel(android.os.Parcel in) {
            return new Abilities(in);
        }

        public Abilities[] newArray(int size) {
            return (new Abilities[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3663682674024872266L;

    protected Abilities(android.os.Parcel in) {
        this.ability = ((Ability) in.readValue((Ability.class.getClassLoader())));
        this.isHidden = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.slot = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Abilities() {
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Abilities.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ability");
        sb.append('=');
        sb.append(((this.ability == null)?"<null>":this.ability));
        sb.append(',');
        sb.append("isHidden");
        sb.append('=');
        sb.append(((this.isHidden == null)?"<null>":this.isHidden));
        sb.append(',');
        sb.append("slot");
        sb.append('=');
        sb.append(((this.slot == null)?"<null>":this.slot));
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
        result = ((result* 31)+((this.ability == null)? 0 :this.ability.hashCode()));
        result = ((result* 31)+((this.slot == null)? 0 :this.slot.hashCode()));
        result = ((result* 31)+((this.isHidden == null)? 0 :this.isHidden.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Abilities) == false) {
            return false;
        }
        Abilities rhs = ((Abilities) other);
        return ((((this.ability == rhs.ability)||((this.ability!= null)&&this.ability.equals(rhs.ability)))&&((this.slot == rhs.slot)||((this.slot!= null)&&this.slot.equals(rhs.slot))))&&((this.isHidden == rhs.isHidden)||((this.isHidden!= null)&&this.isHidden.equals(rhs.isHidden))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(ability);
        dest.writeValue(isHidden);
        dest.writeValue(slot);
    }

    public int describeContents() {
        return  0;
    }

}
