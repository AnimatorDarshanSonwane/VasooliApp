package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class ExpensesData implements Parcelable {
    String id, FromDate, ToDate, Total, Line;

    public ExpensesData() {
    }

    protected ExpensesData(Parcel in) {
        id = in.readString();
        FromDate = in.readString();
        ToDate = in.readString();
        Total = in.readString();
        Line = in.readString();
    }

    public static final Creator<ExpensesData> CREATOR = new Creator<ExpensesData>() {
        @Override
        public ExpensesData createFromParcel(Parcel in) {
            return new ExpensesData(in);
        }

        @Override
        public ExpensesData[] newArray(int size) {
            return new ExpensesData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(FromDate);
        dest.writeString(ToDate);
        dest.writeString(Total);
        dest.writeString(Line);
    }
}
