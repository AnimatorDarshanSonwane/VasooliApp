package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class SiteData implements Parcelable {
    String  siteName, mobileNumber, Name, Address, Agreement, id;

    public SiteData() {

    }

    protected SiteData(Parcel in) {
        siteName = in.readString();
        mobileNumber = in.readString();
        Name = in.readString();
        Address = in.readString();
        Agreement = in.readString();
        id = in.readString();
    }

    public static final Creator<SiteData> CREATOR = new Creator<SiteData>() {
        @Override
        public SiteData createFromParcel(Parcel in) {
            return new SiteData(in);
        }

        @Override
        public SiteData[] newArray(int size) {
            return new SiteData[size];
        }
    };

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAgreement() {
        return Agreement;
    }

    public void setAgreement(String agreement) {
        Agreement = agreement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(siteName);
        dest.writeString(mobileNumber);
        dest.writeString(Name);
        dest.writeString(Address);
        dest.writeString(Agreement);
        dest.writeString(id);
    }
}
