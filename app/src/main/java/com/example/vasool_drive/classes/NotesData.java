package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesData implements Parcelable {
    private String date, note, id;

    public NotesData() {
    }

    protected NotesData(Parcel in) {
        date = in.readString();
        note = in.readString();
        id = in.readString();
    }

    public static final Creator<NotesData> CREATOR = new Creator<NotesData>() {
        @Override
        public NotesData createFromParcel(Parcel in) {
            return new NotesData(in);
        }

        @Override
        public NotesData[] newArray(int size) {
            return new NotesData[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        dest.writeString(note);
        dest.writeString(id);
    }
}
