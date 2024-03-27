package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddExpenseTypeData implements Parcelable {
    private String id, ExpenseTypeName, Status;

    public AddExpenseTypeData() {
    }

    protected AddExpenseTypeData(Parcel in) {
        id = in.readString();
        ExpenseTypeName = in.readString();
        Status = in.readString();
    }

    public static final Creator<AddExpenseTypeData> CREATOR = new Creator<AddExpenseTypeData>() {
        @Override
        public AddExpenseTypeData createFromParcel(Parcel in) {
            return new AddExpenseTypeData(in);
        }

        @Override
        public AddExpenseTypeData[] newArray(int size) {
            return new AddExpenseTypeData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpenseTypeName() {
        return ExpenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        ExpenseTypeName = expenseTypeName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(ExpenseTypeName);
        dest.writeString(Status);
    }
}
