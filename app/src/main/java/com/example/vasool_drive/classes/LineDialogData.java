package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LineDialogData implements Parcelable {
    String lineName, id, position;
    private List<String> AreaName;

    public LineDialogData() {
    }

    protected LineDialogData(Parcel in) {
        lineName = in.readString();
        id = in.readString();
        position = in.readString();
        AreaName = in.createStringArrayList();
    }

    public static final Creator<LineDialogData> CREATOR = new Creator<LineDialogData>() {
        @Override
        public LineDialogData createFromParcel(Parcel in) {
            return new LineDialogData(in);
        }

        @Override
        public LineDialogData[] newArray(int size) {
            return new LineDialogData[size];
        }
    };

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        dest.writeString(lineName);
        dest.writeString(id);
        dest.writeString(position);
        dest.writeStringList(AreaName);
    }
}
