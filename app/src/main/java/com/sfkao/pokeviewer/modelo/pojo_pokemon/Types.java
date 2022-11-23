
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Types implements Serializable, Parcelable
{

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private Type type;
    public final static Creator<Types> CREATOR = new Creator<Types>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Types createFromParcel(android.os.Parcel in) {
            return new Types(in);
        }

        public Types[] newArray(int size) {
            return (new Types[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8370773023804718050L;

    protected Types(android.os.Parcel in) {
        this.slot = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.type = ((Type) in.readValue((Type.class.getClassLoader())));
    }

    public Types() {
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Types.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("slot");
        sb.append('=');
        sb.append(((this.slot == null)?"<null>":this.slot));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.slot == null)? 0 :this.slot.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Types) == false) {
            return false;
        }
        Types rhs = ((Types) other);
        return (((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type)))&&((this.slot == rhs.slot)||((this.slot!= null)&&this.slot.equals(rhs.slot))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(slot);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
