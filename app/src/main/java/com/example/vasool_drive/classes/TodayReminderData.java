package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class TodayReminderData implements Parcelable {
    String date, TodayReminderText, id;

    public TodayReminderData() {
    }

    protected TodayReminderData(Parcel in) {
        date = in.readString();
        TodayReminderText = in.readString();
        id = in.readString();
    }

    public static final Creator<TodayReminderData> CREATOR = new Creator<TodayReminderData>() {
        @Override
        public TodayReminderData createFromParcel(Parcel in) {
            return new TodayReminderData(in);
        }

        @Override
        public TodayReminderData[] newArray(int size) {
            return new TodayReminderData[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodayReminderText() {
        return TodayReminderText;
    }

    public void setTodayReminderText(String todayReminderText) {
        TodayReminderText = todayReminderText;
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
        dest.writeString(date);
        dest.writeString(TodayReminderText);
        dest.writeString(id);
    }
}
