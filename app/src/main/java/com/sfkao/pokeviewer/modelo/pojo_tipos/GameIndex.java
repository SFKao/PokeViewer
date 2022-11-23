
package com.sfkao.pokeviewer.modelo.pojo_tipos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GameIndex {

    @SerializedName("game_index")
    @Expose
    private Integer gameIndex;
    @SerializedName("generation")
    @Expose
    private Generation generation;

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GameIndex.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gameIndex");
        sb.append('=');
        sb.append(((this.gameIndex == null)?"<null>":this.gameIndex));
        sb.append(',');
        sb.append("generation");
        sb.append('=');
        sb.append(((this.generation == null)?"<null>":this.generation));
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
        result = ((result* 31)+((this.generation == null)? 0 :this.generation.hashCode()));
        result = ((result* 31)+((this.gameIndex == null)? 0 :this.gameIndex.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GameIndex) == false) {
            return false;
        }
        GameIndex rhs = ((GameIndex) other);
        return (((this.generation == rhs.generation)||((this.generation!= null)&&this.generation.equals(rhs.generation)))&&((this.gameIndex == rhs.gameIndex)||((this.gameIndex!= null)&&this.gameIndex.equals(rhs.gameIndex))));
    }

}
