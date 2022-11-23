
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pokemon implements Serializable, Parcelable
{

    @SerializedName("abilities")
    @Expose
    private List<Abilities> abilities = new ArrayList<Abilities>();
    @SerializedName("base_experience")
    @Expose
    private Integer baseExperience;
    @SerializedName("forms")
    @Expose
    private List<Form> forms = new ArrayList<Form>();
    @SerializedName("game_indices")
    @Expose
    private List<Object> gameIndices = new ArrayList<Object>();
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("held_items")
    @Expose
    private List<Object> heldItems = new ArrayList<Object>();
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("location_area_encounters")
    @Expose
    private String locationAreaEncounters;
    @SerializedName("moves")
    @Expose
    private List<Moves> moves = new ArrayList<Moves>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("past_types")
    @Expose
    private List<Object> pastTypes = new ArrayList<Object>();
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
        this.baseExperience = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.forms, (Form.class.getClassLoader()));
        in.readList(this.gameIndices, (java.lang.Object.class.getClassLoader()));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.heldItems, (java.lang.Object.class.getClassLoader()));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isDefault = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.locationAreaEncounters = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.moves, (Moves.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.order = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.pastTypes, (java.lang.Object.class.getClassLoader()));
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

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<Object> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<Object> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Object> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<Object> heldItems) {
        this.heldItems = heldItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public void setMoves(List<Moves> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Object> getPastTypes() {
        return pastTypes;
    }

    public void setPastTypes(List<Object> pastTypes) {
        this.pastTypes = pastTypes;
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
        sb.append("baseExperience");
        sb.append('=');
        sb.append(((this.baseExperience == null)?"<null>":this.baseExperience));
        sb.append(',');
        sb.append("forms");
        sb.append('=');
        sb.append(((this.forms == null)?"<null>":this.forms));
        sb.append(',');
        sb.append("gameIndices");
        sb.append('=');
        sb.append(((this.gameIndices == null)?"<null>":this.gameIndices));
        sb.append(',');
        sb.append("height");
        sb.append('=');
        sb.append(((this.height == null)?"<null>":this.height));
        sb.append(',');
        sb.append("heldItems");
        sb.append('=');
        sb.append(((this.heldItems == null)?"<null>":this.heldItems));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("isDefault");
        sb.append('=');
        sb.append(((this.isDefault == null)?"<null>":this.isDefault));
        sb.append(',');
        sb.append("locationAreaEncounters");
        sb.append('=');
        sb.append(((this.locationAreaEncounters == null)?"<null>":this.locationAreaEncounters));
        sb.append(',');
        sb.append("moves");
        sb.append('=');
        sb.append(((this.moves == null)?"<null>":this.moves));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("order");
        sb.append('=');
        sb.append(((this.order == null)?"<null>":this.order));
        sb.append(',');
        sb.append("pastTypes");
        sb.append('=');
        sb.append(((this.pastTypes == null)?"<null>":this.pastTypes));
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
        result = ((result* 31)+((this.heldItems == null)? 0 :this.heldItems.hashCode()));
        result = ((result* 31)+((this.abilities == null)? 0 :this.abilities.hashCode()));
        result = ((result* 31)+((this.isDefault == null)? 0 :this.isDefault.hashCode()));
        result = ((result* 31)+((this.gameIndices == null)? 0 :this.gameIndices.hashCode()));
        result = ((result* 31)+((this.species == null)? 0 :this.species.hashCode()));
        result = ((result* 31)+((this.stats == null)? 0 :this.stats.hashCode()));
        result = ((result* 31)+((this.moves == null)? 0 :this.moves.hashCode()));
        result = ((result* 31)+((this.baseExperience == null)? 0 :this.baseExperience.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.forms == null)? 0 :this.forms.hashCode()));
        result = ((result* 31)+((this.pastTypes == null)? 0 :this.pastTypes.hashCode()));
        result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
        result = ((result* 31)+((this.locationAreaEncounters == null)? 0 :this.locationAreaEncounters.hashCode()));
        result = ((result* 31)+((this.order == null)? 0 :this.order.hashCode()));
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
        return (((((((((((((((((((this.types == rhs.types)||((this.types!= null)&&this.types.equals(rhs.types)))&&((this.weight == rhs.weight)||((this.weight!= null)&&this.weight.equals(rhs.weight))))&&((this.sprites == rhs.sprites)||((this.sprites!= null)&&this.sprites.equals(rhs.sprites))))&&((this.heldItems == rhs.heldItems)||((this.heldItems!= null)&&this.heldItems.equals(rhs.heldItems))))&&((this.abilities == rhs.abilities)||((this.abilities!= null)&&this.abilities.equals(rhs.abilities))))&&((this.isDefault == rhs.isDefault)||((this.isDefault!= null)&&this.isDefault.equals(rhs.isDefault))))&&((this.gameIndices == rhs.gameIndices)||((this.gameIndices!= null)&&this.gameIndices.equals(rhs.gameIndices))))&&((this.species == rhs.species)||((this.species!= null)&&this.species.equals(rhs.species))))&&((this.stats == rhs.stats)||((this.stats!= null)&&this.stats.equals(rhs.stats))))&&((this.moves == rhs.moves)||((this.moves!= null)&&this.moves.equals(rhs.moves))))&&((this.baseExperience == rhs.baseExperience)||((this.baseExperience!= null)&&this.baseExperience.equals(rhs.baseExperience))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.forms == rhs.forms)||((this.forms!= null)&&this.forms.equals(rhs.forms))))&&((this.pastTypes == rhs.pastTypes)||((this.pastTypes!= null)&&this.pastTypes.equals(rhs.pastTypes))))&&((this.height == rhs.height)||((this.height!= null)&&this.height.equals(rhs.height))))&&((this.locationAreaEncounters == rhs.locationAreaEncounters)||((this.locationAreaEncounters!= null)&&this.locationAreaEncounters.equals(rhs.locationAreaEncounters))))&&((this.order == rhs.order)||((this.order!= null)&&this.order.equals(rhs.order))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(abilities);
        dest.writeValue(baseExperience);
        dest.writeList(forms);
        dest.writeList(gameIndices);
        dest.writeValue(height);
        dest.writeList(heldItems);
        dest.writeValue(id);
        dest.writeValue(isDefault);
        dest.writeValue(locationAreaEncounters);
        dest.writeList(moves);
        dest.writeValue(name);
        dest.writeValue(order);
        dest.writeList(pastTypes);
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
