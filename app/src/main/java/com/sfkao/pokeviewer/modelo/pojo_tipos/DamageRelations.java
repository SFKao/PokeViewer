
package com.sfkao.pokeviewer.modelo.pojo_tipos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DamageRelations {

    @SerializedName("double_damage_from")
    @Expose
    private List<DoubleDamageFrom> doubleDamageFrom = null;
    @SerializedName("double_damage_to")
    @Expose
    private List<DoubleDamageTo> doubleDamageTo = null;
    @SerializedName("half_damage_from")
    @Expose
    private List<HalfDamageFrom> halfDamageFrom = null;
    @SerializedName("half_damage_to")
    @Expose
    private List<HalfDamageTo> halfDamageTo = null;
    @SerializedName("no_damage_from")
    @Expose
    private List<NoDamageFrom> noDamageFrom = null;
    @SerializedName("no_damage_to")
    @Expose
    private List<Object> noDamageTo = null;

    public List<DoubleDamageFrom> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<DoubleDamageFrom> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }

    public List<DoubleDamageTo> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public void setDoubleDamageTo(List<DoubleDamageTo> doubleDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<HalfDamageFrom> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public void setHalfDamageFrom(List<HalfDamageFrom> halfDamageFrom) {
        this.halfDamageFrom = halfDamageFrom;
    }

    public List<HalfDamageTo> getHalfDamageTo() {
        return halfDamageTo;
    }

    public void setHalfDamageTo(List<HalfDamageTo> halfDamageTo) {
        this.halfDamageTo = halfDamageTo;
    }

    public List<NoDamageFrom> getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(List<NoDamageFrom> noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public List<Object> getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(List<Object> noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DamageRelations.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("doubleDamageFrom");
        sb.append('=');
        sb.append(((this.doubleDamageFrom == null)?"<null>":this.doubleDamageFrom));
        sb.append(',');
        sb.append("doubleDamageTo");
        sb.append('=');
        sb.append(((this.doubleDamageTo == null)?"<null>":this.doubleDamageTo));
        sb.append(',');
        sb.append("halfDamageFrom");
        sb.append('=');
        sb.append(((this.halfDamageFrom == null)?"<null>":this.halfDamageFrom));
        sb.append(',');
        sb.append("halfDamageTo");
        sb.append('=');
        sb.append(((this.halfDamageTo == null)?"<null>":this.halfDamageTo));
        sb.append(',');
        sb.append("noDamageFrom");
        sb.append('=');
        sb.append(((this.noDamageFrom == null)?"<null>":this.noDamageFrom));
        sb.append(',');
        sb.append("noDamageTo");
        sb.append('=');
        sb.append(((this.noDamageTo == null)?"<null>":this.noDamageTo));
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
        result = ((result* 31)+((this.doubleDamageTo == null)? 0 :this.doubleDamageTo.hashCode()));
        result = ((result* 31)+((this.noDamageFrom == null)? 0 :this.noDamageFrom.hashCode()));
        result = ((result* 31)+((this.noDamageTo == null)? 0 :this.noDamageTo.hashCode()));
        result = ((result* 31)+((this.doubleDamageFrom == null)? 0 :this.doubleDamageFrom.hashCode()));
        result = ((result* 31)+((this.halfDamageTo == null)? 0 :this.halfDamageTo.hashCode()));
        result = ((result* 31)+((this.halfDamageFrom == null)? 0 :this.halfDamageFrom.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DamageRelations) == false) {
            return false;
        }
        DamageRelations rhs = ((DamageRelations) other);
        return (((((((this.doubleDamageTo == rhs.doubleDamageTo)||((this.doubleDamageTo!= null)&&this.doubleDamageTo.equals(rhs.doubleDamageTo)))&&((this.noDamageFrom == rhs.noDamageFrom)||((this.noDamageFrom!= null)&&this.noDamageFrom.equals(rhs.noDamageFrom))))&&((this.noDamageTo == rhs.noDamageTo)||((this.noDamageTo!= null)&&this.noDamageTo.equals(rhs.noDamageTo))))&&((this.doubleDamageFrom == rhs.doubleDamageFrom)||((this.doubleDamageFrom!= null)&&this.doubleDamageFrom.equals(rhs.doubleDamageFrom))))&&((this.halfDamageTo == rhs.halfDamageTo)||((this.halfDamageTo!= null)&&this.halfDamageTo.equals(rhs.halfDamageTo))))&&((this.halfDamageFrom == rhs.halfDamageFrom)||((this.halfDamageFrom!= null)&&this.halfDamageFrom.equals(rhs.halfDamageFrom))));
    }

}
