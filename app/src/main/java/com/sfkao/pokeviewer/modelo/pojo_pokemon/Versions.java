
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Versions implements Serializable, Parcelable
{

    @SerializedName("generation-i")
    @Expose
    private GenerationI generationI;
    @SerializedName("generation-ii")
    @Expose
    private GenerationIi generationIi;
    @SerializedName("generation-iii")
    @Expose
    private GenerationIii generationIii;
    @SerializedName("generation-iv")
    @Expose
    private GenerationIv generationIv;
    @SerializedName("generation-v")
    @Expose
    private GenerationV generationV;
    @SerializedName("generation-vi")
    @Expose
    private GenerationVi generationVi;
    @SerializedName("generation-vii")
    @Expose
    private GenerationVii generationVii;
    @SerializedName("generation-viii")
    @Expose
    private GenerationViii generationViii;
    public final static Creator<Versions> CREATOR = new Creator<Versions>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Versions createFromParcel(android.os.Parcel in) {
            return new Versions(in);
        }

        public Versions[] newArray(int size) {
            return (new Versions[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2218267523922677994L;

    protected Versions(android.os.Parcel in) {
        this.generationI = ((GenerationI) in.readValue((GenerationI.class.getClassLoader())));
        this.generationIi = ((GenerationIi) in.readValue((GenerationIi.class.getClassLoader())));
        this.generationIii = ((GenerationIii) in.readValue((GenerationIii.class.getClassLoader())));
        this.generationIv = ((GenerationIv) in.readValue((GenerationIv.class.getClassLoader())));
        this.generationV = ((GenerationV) in.readValue((GenerationV.class.getClassLoader())));
        this.generationVi = ((GenerationVi) in.readValue((GenerationVi.class.getClassLoader())));
        this.generationVii = ((GenerationVii) in.readValue((GenerationVii.class.getClassLoader())));
        this.generationViii = ((GenerationViii) in.readValue((GenerationViii.class.getClassLoader())));
    }

    public Versions() {
    }

    public GenerationI getGenerationI() {
        return generationI;
    }

    public void setGenerationI(GenerationI generationI) {
        this.generationI = generationI;
    }

    public GenerationIi getGenerationIi() {
        return generationIi;
    }

    public void setGenerationIi(GenerationIi generationIi) {
        this.generationIi = generationIi;
    }

    public GenerationIii getGenerationIii() {
        return generationIii;
    }

    public void setGenerationIii(GenerationIii generationIii) {
        this.generationIii = generationIii;
    }

    public GenerationIv getGenerationIv() {
        return generationIv;
    }

    public void setGenerationIv(GenerationIv generationIv) {
        this.generationIv = generationIv;
    }

    public GenerationV getGenerationV() {
        return generationV;
    }

    public void setGenerationV(GenerationV generationV) {
        this.generationV = generationV;
    }

    public GenerationVi getGenerationVi() {
        return generationVi;
    }

    public void setGenerationVi(GenerationVi generationVi) {
        this.generationVi = generationVi;
    }

    public GenerationVii getGenerationVii() {
        return generationVii;
    }

    public void setGenerationVii(GenerationVii generationVii) {
        this.generationVii = generationVii;
    }

    public GenerationViii getGenerationViii() {
        return generationViii;
    }

    public void setGenerationViii(GenerationViii generationViii) {
        this.generationViii = generationViii;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Versions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("generationI");
        sb.append('=');
        sb.append(((this.generationI == null)?"<null>":this.generationI));
        sb.append(',');
        sb.append("generationIi");
        sb.append('=');
        sb.append(((this.generationIi == null)?"<null>":this.generationIi));
        sb.append(',');
        sb.append("generationIii");
        sb.append('=');
        sb.append(((this.generationIii == null)?"<null>":this.generationIii));
        sb.append(',');
        sb.append("generationIv");
        sb.append('=');
        sb.append(((this.generationIv == null)?"<null>":this.generationIv));
        sb.append(',');
        sb.append("generationV");
        sb.append('=');
        sb.append(((this.generationV == null)?"<null>":this.generationV));
        sb.append(',');
        sb.append("generationVi");
        sb.append('=');
        sb.append(((this.generationVi == null)?"<null>":this.generationVi));
        sb.append(',');
        sb.append("generationVii");
        sb.append('=');
        sb.append(((this.generationVii == null)?"<null>":this.generationVii));
        sb.append(',');
        sb.append("generationViii");
        sb.append('=');
        sb.append(((this.generationViii == null)?"<null>":this.generationViii));
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
        result = ((result* 31)+((this.generationV == null)? 0 :this.generationV.hashCode()));
        result = ((result* 31)+((this.generationIii == null)? 0 :this.generationIii.hashCode()));
        result = ((result* 31)+((this.generationIv == null)? 0 :this.generationIv.hashCode()));
        result = ((result* 31)+((this.generationVii == null)? 0 :this.generationVii.hashCode()));
        result = ((result* 31)+((this.generationVi == null)? 0 :this.generationVi.hashCode()));
        result = ((result* 31)+((this.generationIi == null)? 0 :this.generationIi.hashCode()));
        result = ((result* 31)+((this.generationViii == null)? 0 :this.generationViii.hashCode()));
        result = ((result* 31)+((this.generationI == null)? 0 :this.generationI.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Versions) == false) {
            return false;
        }
        Versions rhs = ((Versions) other);
        return (((((((((this.generationV == rhs.generationV)||((this.generationV!= null)&&this.generationV.equals(rhs.generationV)))&&((this.generationIii == rhs.generationIii)||((this.generationIii!= null)&&this.generationIii.equals(rhs.generationIii))))&&((this.generationIv == rhs.generationIv)||((this.generationIv!= null)&&this.generationIv.equals(rhs.generationIv))))&&((this.generationVii == rhs.generationVii)||((this.generationVii!= null)&&this.generationVii.equals(rhs.generationVii))))&&((this.generationVi == rhs.generationVi)||((this.generationVi!= null)&&this.generationVi.equals(rhs.generationVi))))&&((this.generationIi == rhs.generationIi)||((this.generationIi!= null)&&this.generationIi.equals(rhs.generationIi))))&&((this.generationViii == rhs.generationViii)||((this.generationViii!= null)&&this.generationViii.equals(rhs.generationViii))))&&((this.generationI == rhs.generationI)||((this.generationI!= null)&&this.generationI.equals(rhs.generationI))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(generationI);
        dest.writeValue(generationIi);
        dest.writeValue(generationIii);
        dest.writeValue(generationIv);
        dest.writeValue(generationV);
        dest.writeValue(generationVi);
        dest.writeValue(generationVii);
        dest.writeValue(generationViii);
    }

    public int describeContents() {
        return  0;
    }

}
