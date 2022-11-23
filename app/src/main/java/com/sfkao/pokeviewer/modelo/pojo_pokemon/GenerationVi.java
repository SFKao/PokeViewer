
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GenerationVi implements Serializable, Parcelable
{

    @SerializedName("omegaruby-alphasapphire")
    @Expose
    private OmegarubyAlphasapphire omegarubyAlphasapphire;
    @SerializedName("x-y")
    @Expose
    private XY xY;
    public final static Creator<GenerationVi> CREATOR = new Creator<GenerationVi>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GenerationVi createFromParcel(android.os.Parcel in) {
            return new GenerationVi(in);
        }

        public GenerationVi[] newArray(int size) {
            return (new GenerationVi[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7795612112269215770L;

    protected GenerationVi(android.os.Parcel in) {
        this.omegarubyAlphasapphire = ((OmegarubyAlphasapphire) in.readValue((OmegarubyAlphasapphire.class.getClassLoader())));
        this.xY = ((XY) in.readValue((XY.class.getClassLoader())));
    }

    public GenerationVi() {
    }

    public OmegarubyAlphasapphire getOmegarubyAlphasapphire() {
        return omegarubyAlphasapphire;
    }

    public void setOmegarubyAlphasapphire(OmegarubyAlphasapphire omegarubyAlphasapphire) {
        this.omegarubyAlphasapphire = omegarubyAlphasapphire;
    }

    public XY getxY() {
        return xY;
    }

    public void setxY(XY xY) {
        this.xY = xY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GenerationVi.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("omegarubyAlphasapphire");
        sb.append('=');
        sb.append(((this.omegarubyAlphasapphire == null)?"<null>":this.omegarubyAlphasapphire));
        sb.append(',');
        sb.append("xY");
        sb.append('=');
        sb.append(((this.xY == null)?"<null>":this.xY));
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
        result = ((result* 31)+((this.xY == null)? 0 :this.xY.hashCode()));
        result = ((result* 31)+((this.omegarubyAlphasapphire == null)? 0 :this.omegarubyAlphasapphire.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GenerationVi) == false) {
            return false;
        }
        GenerationVi rhs = ((GenerationVi) other);
        return (((this.xY == rhs.xY)||((this.xY!= null)&&this.xY.equals(rhs.xY)))&&((this.omegarubyAlphasapphire == rhs.omegarubyAlphasapphire)||((this.omegarubyAlphasapphire!= null)&&this.omegarubyAlphasapphire.equals(rhs.omegarubyAlphasapphire))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(omegarubyAlphasapphire);
        dest.writeValue(xY);
    }

    public int describeContents() {
        return  0;
    }

}
