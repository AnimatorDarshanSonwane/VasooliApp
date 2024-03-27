package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {
    String name, emailID, primarySite, id, mobileNumber, deviceID;

    public ProfileData() {
    }


    protected ProfileData(Parcel in) {
        name = in.readString();
        emailID = in.readString();
        primarySite = in.readString();
        id = in.readString();
        mobileNumber = in.readString();
        deviceID =in.readString();
    }

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPrimarySite() {
        return primarySite;
    }

    public void setPrimarySite(String primarySite) {
        this.primarySite = primarySite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(emailID);
        dest.writeString(primarySite);
        dest.writeString(id);
        dest.writeString(mobileNumber);
        dest.writeString(deviceID);
    }
}
