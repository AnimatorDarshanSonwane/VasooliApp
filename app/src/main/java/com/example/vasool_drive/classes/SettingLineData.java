package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SettingLineData implements Parcelable {
    private String LineName, LineType, id;
    private String BillAmountPerHundread,NoOfInstall,BadLoanDays , AreaNames, AreaId;

    private List<String> AreaName;

    public SettingLineData() {
    }

    protected SettingLineData(Parcel in) {
        LineName = in.readString();
        LineType = in.readString();
        id = in.readString();
        BillAmountPerHundread = in.readString();
        NoOfInstall = in.readString();
        BadLoanDays = in.readString();
        AreaNames = in.readString();
        AreaId = in.readString();
        AreaName = in.createStringArrayList();
    }

    public static final Creator<SettingLineData> CREATOR = new Creator<SettingLineData>() {
        @Override
        public SettingLineData createFromParcel(Parcel in) {
            return new SettingLineData(in);
        }

        @Override
        public SettingLineData[] newArray(int size) {
            return new SettingLineData[size];
        }
    };

    public String getLineName() {
        return LineName;
    }

    public void setLineName(String lineName) {
        LineName = lineName;
    }

    public String getLineType() {
        return LineType;
    }

    public void setLineType(String lineType) {
        LineType = lineType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillAmountPerHundread() {
        return BillAmountPerHundread;
    }

    public void setBillAmountPerHundread(String billAmountPerHundread) {
        BillAmountPerHundread = billAmountPerHundread;
    }

    public String getNoOfInstall() {
        return NoOfInstall;
    }

    public void setNoOfInstall(String noOfInstall) {
        NoOfInstall = noOfInstall;
    }

    public String getBadLoanDays() {
        return BadLoanDays;
    }

    public void setBadLoanDays(String badLoanDays) {
        BadLoanDays = badLoanDays;
    }

    public String getAreaNames() {
        return AreaNames;
    }

    public void setAreaNames(String areaNames) {
        AreaNames = areaNames;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
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
        dest.writeString(LineName);
        dest.writeString(LineType);
        dest.writeString(id);
        dest.writeString(BillAmountPerHundread);
        dest.writeString(NoOfInstall);
        dest.writeString(BadLoanDays);
        dest.writeString(AreaNames);
        dest.writeString(AreaId);
        dest.writeStringList(AreaName);
    }
}
