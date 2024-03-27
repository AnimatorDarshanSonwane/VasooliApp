package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddAreaData implements Parcelable {
    private String id;
    private List<String> AreaName;

    public AddAreaData() {
    }

    protected AddAreaData(Parcel in) {
        id = in.readString();
        AreaName = in.createStringArrayList();
    }

    public static final Creator<AddAreaData> CREATOR = new Creator<AddAreaData>() {
        @Override
        public AddAreaData createFromParcel(Parcel in) {
            return new AddAreaData(in);
        }

        @Override
        public AddAreaData[] newArray(int size) {
            return new AddAreaData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAreaName() {
        return AreaName;
    }

    public void setAreaName(List<String> areaName) {
        AreaName = areaName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeStringList(AreaName);
    }
}
