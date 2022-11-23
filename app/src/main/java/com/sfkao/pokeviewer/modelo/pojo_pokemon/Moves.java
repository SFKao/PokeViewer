
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Moves implements Serializable, Parcelable
{

    @SerializedName("move")
    @Expose
    private Move move;
    @SerializedName("version_group_details")
    @Expose
    private List<VersionGroupDetail> versionGroupDetails = new ArrayList<VersionGroupDetail>();
    public final static Creator<Moves> CREATOR = new Creator<Moves>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Moves createFromParcel(android.os.Parcel in) {
            return new Moves(in);
        }

        public Moves[] newArray(int size) {
            return (new Moves[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2182368194369134958L;

    protected Moves(android.os.Parcel in) {
        this.move = ((Move) in.readValue((Move.class.getClassLoader())));
        in.readList(this.versionGroupDetails, (VersionGroupDetail.class.getClassLoader()));
    }

    public Moves() {
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Moves.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("move");
        sb.append('=');
        sb.append(((this.move == null)?"<null>":this.move));
        sb.append(',');
        sb.append("versionGroupDetails");
        sb.append('=');
        sb.append(((this.versionGroupDetails == null)?"<null>":this.versionGroupDetails));
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
        result = ((result* 31)+((this.move == null)? 0 :this.move.hashCode()));
        result = ((result* 31)+((this.versionGroupDetails == null)? 0 :this.versionGroupDetails.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Moves) == false) {
            return false;
        }
        Moves rhs = ((Moves) other);
        return (((this.move == rhs.move)||((this.move!= null)&&this.move.equals(rhs.move)))&&((this.versionGroupDetails == rhs.versionGroupDetails)||((this.versionGroupDetails!= null)&&this.versionGroupDetails.equals(rhs.versionGroupDetails))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(move);
        dest.writeList(versionGroupDetails);
    }

    public int describeContents() {
        return  0;
    }

}
