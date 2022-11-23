
package com.sfkao.pokeviewer.modelo.pojo_pokemon;

import java.io.Serializable;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VersionGroupDetail implements Serializable, Parcelable
{

    @SerializedName("level_learned_at")
    @Expose
    private Integer levelLearnedAt;
    @SerializedName("move_learn_method")
    @Expose
    private MoveLearnMethod moveLearnMethod;
    @SerializedName("version_group")
    @Expose
    private VersionGroup versionGroup;
    public final static Creator<VersionGroupDetail> CREATOR = new Creator<VersionGroupDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VersionGroupDetail createFromParcel(android.os.Parcel in) {
            return new VersionGroupDetail(in);
        }

        public VersionGroupDetail[] newArray(int size) {
            return (new VersionGroupDetail[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3825976899702424297L;

    protected VersionGroupDetail(android.os.Parcel in) {
        this.levelLearnedAt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.moveLearnMethod = ((MoveLearnMethod) in.readValue((MoveLearnMethod.class.getClassLoader())));
        this.versionGroup = ((VersionGroup) in.readValue((VersionGroup.class.getClassLoader())));
    }

    public VersionGroupDetail() {
    }

    public Integer getLevelLearnedAt() {
        return levelLearnedAt;
    }

    public void setLevelLearnedAt(Integer levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }

    public MoveLearnMethod getMoveLearnMethod() {
        return moveLearnMethod;
    }

    public void setMoveLearnMethod(MoveLearnMethod moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(VersionGroupDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("levelLearnedAt");
        sb.append('=');
        sb.append(((this.levelLearnedAt == null)?"<null>":this.levelLearnedAt));
        sb.append(',');
        sb.append("moveLearnMethod");
        sb.append('=');
        sb.append(((this.moveLearnMethod == null)?"<null>":this.moveLearnMethod));
        sb.append(',');
        sb.append("versionGroup");
        sb.append('=');
        sb.append(((this.versionGroup == null)?"<null>":this.versionGroup));
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
        result = ((result* 31)+((this.moveLearnMethod == null)? 0 :this.moveLearnMethod.hashCode()));
        result = ((result* 31)+((this.versionGroup == null)? 0 :this.versionGroup.hashCode()));
        result = ((result* 31)+((this.levelLearnedAt == null)? 0 :this.levelLearnedAt.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VersionGroupDetail) == false) {
            return false;
        }
        VersionGroupDetail rhs = ((VersionGroupDetail) other);
        return ((((this.moveLearnMethod == rhs.moveLearnMethod)||((this.moveLearnMethod!= null)&&this.moveLearnMethod.equals(rhs.moveLearnMethod)))&&((this.versionGroup == rhs.versionGroup)||((this.versionGroup!= null)&&this.versionGroup.equals(rhs.versionGroup))))&&((this.levelLearnedAt == rhs.levelLearnedAt)||((this.levelLearnedAt!= null)&&this.levelLearnedAt.equals(rhs.levelLearnedAt))));
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(levelLearnedAt);
        dest.writeValue(moveLearnMethod);
        dest.writeValue(versionGroup);
    }

    public int describeContents() {
        return  0;
    }

}
