package com.example.mobilnakasa;

import android.os.Parcel;
import android.os.Parcelable;

public class OperatorItem implements Parcelable {
    private String id;
    private int imgResource;
    private String name;
    private String role;

    public OperatorItem(String id, int imgResource, String name, String role) {
        this.id = id;
        this.imgResource = imgResource;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public int getImgResource() {
        return imgResource;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeInt(imgResource);
        out.writeString(name);
        out.writeString(role);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<OperatorItem> CREATOR = new Parcelable.Creator<OperatorItem>() {
        public OperatorItem createFromParcel(Parcel in) {
            return new OperatorItem(in);
        }

        public OperatorItem[] newArray(int size) {
            return new OperatorItem[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private OperatorItem(Parcel in) {
        id = in.readString();
        imgResource = in.readInt();
        name = in.readString();
        role = in.readString();
    }

}
