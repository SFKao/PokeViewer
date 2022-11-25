
package com.sfkao.pokeviewer.modelo.pojo_tipos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase POJO para almacenar un tipo al buscarlo en la api
 * Creado con https://www.jsonschema2pojo.org
 */
public class Tipo {

    @SerializedName("damage_relations")
    @Expose
    private DamageRelations damageRelations;
    @SerializedName("game_indices")
    @Expose
    private List<GameIndex> gameIndices = null;
    @SerializedName("generation")
    @Expose
    private Generation__1 generation;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("move_damage_class")
    @Expose
    private MoveDamageClass moveDamageClass;
    @SerializedName("moves")
    @Expose
    private List<Move> moves = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("names")
    @Expose
    private List<Name> names = null;
    @SerializedName("past_damage_relations")
    @Expose
    private List<Object> pastDamageRelations = null;
    @SerializedName("pokemon")
    @Expose
    private List<Pokemon> pokemon = null;

    public DamageRelations getDamageRelations() {
        return damageRelations;
    }

    public void setDamageRelations(DamageRelations damageRelations) {
        this.damageRelations = damageRelations;
    }

    public List<GameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<GameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public Generation__1 getGeneration() {
        return generation;
    }

    public void setGeneration(Generation__1 generation) {
        this.generation = generation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MoveDamageClass getMoveDamageClass() {
        return moveDamageClass;
    }

    public void setMoveDamageClass(MoveDamageClass moveDamageClass) {
        this.moveDamageClass = moveDamageClass;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<Object> getPastDamageRelations() {
        return pastDamageRelations;
    }

    public void setPastDamageRelations(List<Object> pastDamageRelations) {
        this.pastDamageRelations = pastDamageRelations;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tipo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("damageRelations");
        sb.append('=');
        sb.append(((this.damageRelations == null)?"<null>":this.damageRelations));
        sb.append(',');
        sb.append("gameIndices");
        sb.append('=');
        sb.append(((this.gameIndices == null)?"<null>":this.gameIndices));
        sb.append(',');
        sb.append("generation");
        sb.append('=');
        sb.append(((this.generation == null)?"<null>":this.generation));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("moveDamageClass");
        sb.append('=');
        sb.append(((this.moveDamageClass == null)?"<null>":this.moveDamageClass));
        sb.append(',');
        sb.append("moves");
        sb.append('=');
        sb.append(((this.moves == null)?"<null>":this.moves));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("names");
        sb.append('=');
        sb.append(((this.names == null)?"<null>":this.names));
        sb.append(',');
        sb.append("pastDamageRelations");
        sb.append('=');
        sb.append(((this.pastDamageRelations == null)?"<null>":this.pastDamageRelations));
        sb.append(',');
        sb.append("pokemon");
        sb.append('=');
        sb.append(((this.pokemon == null)?"<null>":this.pokemon));
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
        result = ((result* 31)+((this.moveDamageClass == null)? 0 :this.moveDamageClass.hashCode()));
        result = ((result* 31)+((this.names == null)? 0 :this.names.hashCode()));
        result = ((result* 31)+((this.pokemon == null)? 0 :this.pokemon.hashCode()));
        result = ((result* 31)+((this.gameIndices == null)? 0 :this.gameIndices.hashCode()));
        result = ((result* 31)+((this.moves == null)? 0 :this.moves.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.pastDamageRelations == null)? 0 :this.pastDamageRelations.hashCode()));
        result = ((result* 31)+((this.damageRelations == null)? 0 :this.damageRelations.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tipo) == false) {
            return false;
        }
        Tipo rhs = ((Tipo) other);
        return (((((((((((this.generation == rhs.generation)||((this.generation!= null)&&this.generation.equals(rhs.generation)))&&((this.moveDamageClass == rhs.moveDamageClass)||((this.moveDamageClass!= null)&&this.moveDamageClass.equals(rhs.moveDamageClass))))&&((this.names == rhs.names)||((this.names!= null)&&this.names.equals(rhs.names))))&&((this.pokemon == rhs.pokemon)||((this.pokemon!= null)&&this.pokemon.equals(rhs.pokemon))))&&((this.gameIndices == rhs.gameIndices)||((this.gameIndices!= null)&&this.gameIndices.equals(rhs.gameIndices))))&&((this.moves == rhs.moves)||((this.moves!= null)&&this.moves.equals(rhs.moves))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.pastDamageRelations == rhs.pastDamageRelations)||((this.pastDamageRelations!= null)&&this.pastDamageRelations.equals(rhs.pastDamageRelations))))&&((this.damageRelations == rhs.damageRelations)||((this.damageRelations!= null)&&this.damageRelations.equals(rhs.damageRelations))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))));
    }

}
