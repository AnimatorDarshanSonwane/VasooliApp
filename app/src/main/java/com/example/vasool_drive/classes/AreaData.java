package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class AreaData implements Parcelable {
    String id, AreaName, LineId, lineName;

    public AreaData() {
    }

    protected AreaData(Parcel in) {
        id = in.readString();
        AreaName = in.readString();
        LineId = in.readString();
        lineName = in.readString();
    }

    public static final Creator<AreaData> CREATOR = new Creator<AreaData>() {
        @Override
        public AreaData createFromParcel(Parcel in) {
            return new AreaData(in);
        }

        @Override
        public AreaData[] newArray(int size) {
            return new AreaData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getLineId() {
        return LineId;
    }

    public void setLineId(String lineId) {
        LineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(AreaName);
        dest.writeString(LineId);
        dest.writeString(lineName);
    }
}
