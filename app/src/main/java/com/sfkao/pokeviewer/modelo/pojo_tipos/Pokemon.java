
package com.sfkao.pokeviewer.modelo.pojo_tipos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pokemon {

    @SerializedName("pokemon")
    @Expose
    private Pokemon__1 pokemon;
    @SerializedName("slot")
    @Expose
    private Integer slot;

    public Pokemon__1 getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon__1 pokemon) {
        this.pokemon = pokemon;
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
        sb.append(Pokemon.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pokemon");
        sb.append('=');
        sb.append(((this.pokemon == null)?"<null>":this.pokemon));
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
        result = ((result* 31)+((this.pokemon == null)? 0 :this.pokemon.hashCode()));
        result = ((result* 31)+((this.slot == null)? 0 :this.slot.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pokemon) == false) {
            return false;
        }
        Pokemon rhs = ((Pokemon) other);
        return (((this.pokemon == rhs.pokemon)||((this.pokemon!= null)&&this.pokemon.equals(rhs.pokemon)))&&((this.slot == rhs.slot)||((this.slot!= null)&&this.slot.equals(rhs.slot))));
    }

}
