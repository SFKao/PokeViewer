
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Other implements Serializable, Parcelable
{

    @SerializedName("dream_world")
    @Expose
    private DreamWorld dreamWorld;
    @SerializedName("home")
    @Expose
    private Home home;
    @SerializedName("official-artwork")
    @Expose
    private OfficialArtwork officialArtwork;
    public final static Creator<Other> CREATOR = new Creator<Other>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Other createFromParcel(android.os.Parcel in) {
            return new Other(in);
        }

        public Other[] newArray(int size) {
            return (new Other[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3155902518607265437L;

    protected Other(android.os.Parcel in) {
        this.dreamWorld = ((DreamWorld) in.readValue((DreamWorld.class.getClassLoader())));
        this.home = ((Home) in.readValue((Home.class.getClassLoader())));
        this.officialArtwork = ((OfficialArtwork) in.readValue((OfficialArtwork.class.getClassLoader())));
    }

    public Other() {
    }

    public DreamWorld getDreamWorld() {
        return dreamWorld;
    }

    public void setDreamWorld(DreamWorld dreamWorld) {
        this.dreamWorld = dreamWorld;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public OfficialArtwork getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(OfficialArtwork officialArtwork) {
        this.officialArtwork = officialArtwork;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Other.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dreamWorld");
        sb.append('=');
        sb.append(((this.dreamWorld == null)?"<null>":this.dreamWorld));
        sb.append(',');
        sb.append("home");
        sb.append('=');
        sb.append(((this.home == null)?"<null>":this.home));
        sb.append(',');
        sb.append("officialArtwork");
        sb.append('=');
        sb.append(((this.officialArtwork == null)?"<null>":this.officialArtwork));
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
        result = ((result* 31)+((this.officialArtwork == null)? 0 :this.officialArtwork.hashCode()));
        result = ((result* 31)+((this.dreamWorld == null)? 0 :this.dreamWorld.hashCode()));
        result = ((result* 31)+((this.home == null)? 0 :this.home.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Other) == false) {
            return false;
        }
        Other rhs = ((Other) other);
        return ((((this.officialArtwork == rhs.officialArtwork)||((this.officialArtwork!= null)&&this.officialArtwork.equals(rhs.officialArtwork)))&&((this.dreamWorld == rhs.dreamWorld)||((this.dreamWorld!= null)&&this.dreamWorld.equals(rhs.dreamWorld))))&&((this.home == rhs.home)||((this.home!= null)&&this.home.equals(rhs.home))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(dreamWorld);
        dest.writeValue(home);
        dest.writeValue(officialArtwork);
    }

    public int describeContents() {
        return  0;
    }

}
