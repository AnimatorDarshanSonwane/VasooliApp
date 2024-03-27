package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

import com.example.vasool_drive.adpters.BottomNavViewPagerAdapter;
import com.tomash.androidcontacts.contactgetter.entity.ContactData;

public class ContactDatas extends ContactData implements Parcelable {

    private String mySuperField, contactName, contactNumber, contactPhoto;
    private SparseBooleanArray mSelectedItemsIds;

    public ContactDatas() {
    }

    protected ContactDatas(Parcel in) {
        mySuperField = in.readString();
        contactName = in.readString();
        contactNumber = in.readString();
        contactPhoto = in.readString();
        mSelectedItemsIds = in.readSparseBooleanArray();
    }

    public static final Creator<ContactDatas> CREATOR = new Creator<ContactDatas>() {
        @Override
        public ContactDatas createFromParcel(Parcel in) {
            return new ContactDatas(in);
        }

        @Override
        public ContactDatas[] newArray(int size) {
            return new ContactDatas[size];
        }
    };

    public String getMySuperField() {
        return mySuperField;
    }

    public void setMySuperField(String mySuperField) {
        this.mySuperField = mySuperField;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactPhoto() {
        return contactPhoto;
    }

    public void setContactPhoto(String contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public SparseBooleanArray getmSelectedItemsIds() {
        return mSelectedItemsIds;
    }

    public void setmSelectedItemsIds(SparseBooleanArray mSelectedItemsIds) {
        this.mSelectedItemsIds = mSelectedItemsIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mySuperField);
        dest.writeString(contactName);
        dest.writeString(contactNumber);
        dest.writeString(contactPhoto);
        dest.writeSparseBooleanArray(mSelectedItemsIds);
    }
}
