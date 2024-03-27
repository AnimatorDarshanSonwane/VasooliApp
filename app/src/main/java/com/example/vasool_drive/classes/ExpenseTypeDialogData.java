package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class ExpenseTypeDialogData implements Parcelable {
    String expenseTypeName, status, id;

    public ExpenseTypeDialogData() {
    }

    protected ExpenseTypeDialogData(Parcel in) {
        expenseTypeName = in.readString();
        status = in.readString();
        id = in.readString();
    }

    public static final Creator<ExpenseTypeDialogData> CREATOR = new Creator<ExpenseTypeDialogData>() {
        @Override
        public ExpenseTypeDialogData createFromParcel(Parcel in) {
            return new ExpenseTypeDialogData(in);
        }

        @Override
        public ExpenseTypeDialogData[] newArray(int size) {
            return new ExpenseTypeDialogData[size];
        }
    };

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        this.expenseTypeName = expenseTypeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        dest.writeString(expenseTypeName);
        dest.writeString(status);
        dest.writeString(id);
    }
}
