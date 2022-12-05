
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase POJO para almacenar un pokemon al buscarlo en la api
 * Creado con https://www.jsonschema2pojo.org
 */
public class Pokemon implements Serializable, Parcelable
{

    @SerializedName("abilities")
    @Expose
    private List<Abilities> abilities = new ArrayList<Abilities>();

    @SerializedName("forms")
    @Expose
    private List<Form> forms = new ArrayList<Form>();

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("species")
    @Expose
    private Species species;
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    @SerializedName("stats")
    @Expose
    private List<Stats> stats = new ArrayList<Stats>();
    @SerializedName("types")
    @Expose
    private List<Types> types = new ArrayList<Types>();
    @SerializedName("weight")
    @Expose
    private Integer weight;

    public final static Creator<Pokemon> CREATOR = new Creator<Pokemon>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pokemon createFromParcel(android.os.Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return (new Pokemon[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1163681908780300940L;

    protected Pokemon(android.os.Parcel in) {
        in.readList(this.abilities, (Abilities.class.getClassLoader()));
        in.readList(this.forms, (Form.class.getClassLoader()));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.species = ((Species) in.readValue((Species.class.getClassLoader())));
        this.sprites = ((Sprites) in.readValue((Sprites.class.getClassLoader())));
        in.readList(this.stats, (Stats.class.getClassLoader()));
        in.readList(this.types, (Types.class.getClassLoader()));
        this.weight = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Pokemon() {
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }



    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Pokemon.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("abilities");
        sb.append('=');
        sb.append(((this.abilities == null)?"<null>":this.abilities));
        sb.append(',');
        sb.append("forms");
        sb.append('=');
        sb.append(((this.forms == null)?"<null>":this.forms));
        sb.append(',');
        sb.append("height");
        sb.append('=');
        sb.append(((this.height == null)?"<null>":this.height));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("species");
        sb.append('=');
        sb.append(((this.species == null)?"<null>":this.species));
        sb.append(',');
        sb.append("sprites");
        sb.append('=');
        sb.append(((this.sprites == null)?"<null>":this.sprites));
        sb.append(',');
        sb.append("stats");
        sb.append('=');
        sb.append(((this.stats == null)?"<null>":this.stats));
        sb.append(',');
        sb.append("types");
        sb.append('=');
        sb.append(((this.types == null)?"<null>":this.types));
        sb.append(',');
        sb.append("weight");
        sb.append('=');
        sb.append(((this.weight == null)?"<null>":this.weight));
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
        result = ((result* 31)+((this.types == null)? 0 :this.types.hashCode()));
        result = ((result* 31)+((this.weight == null)? 0 :this.weight.hashCode()));
        result = ((result* 31)+((this.sprites == null)? 0 :this.sprites.hashCode()));
        result = ((result* 31)+((this.abilities == null)? 0 :this.abilities.hashCode()));
        result = ((result* 31)+((this.species == null)? 0 :this.species.hashCode()));
        result = ((result* 31)+((this.stats == null)? 0 :this.stats.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.forms == null)? 0 :this.forms.hashCode()));
        result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(abilities, pokemon.abilities) && Objects.equals(forms, pokemon.forms) && Objects.equals(height, pokemon.height) && Objects.equals(id, pokemon.id) && Objects.equals(name, pokemon.name) && Objects.equals(species, pokemon.species) && Objects.equals(sprites, pokemon.sprites) && Objects.equals(stats, pokemon.stats) && Objects.equals(types, pokemon.types) && Objects.equals(weight, pokemon.weight);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(abilities);
        dest.writeList(forms);
        dest.writeValue(height);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(species);
        dest.writeValue(sprites);
        dest.writeList(stats);
        dest.writeList(types);
        dest.writeValue(weight);
    }

    public int describeContents() {
        return  0;
    }

}
