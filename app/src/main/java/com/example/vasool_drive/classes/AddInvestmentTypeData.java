package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class AddInvestmentTypeData implements Parcelable {

    String investmentTypeName;
    String id;
    String status;
    String expenseType;
    String expenseTypeId;

    int expenseTypePosition;

    public AddInvestmentTypeData() {
    }

    protected AddInvestmentTypeData(Parcel in) {
        investmentTypeName = in.readString();
        id = in.readString();
        status = in.readString();
        expenseType = in.readString();
        expenseTypeId = in.readString();
        expenseTypePosition = in.readInt();
    }

    public static final Creator<AddInvestmentTypeData> CREATOR = new Creator<AddInvestmentTypeData>() {
        @Override
        public AddInvestmentTypeData createFromParcel(Parcel in) {
            return new AddInvestmentTypeData(in);
        }

        @Override
        public AddInvestmentTypeData[] newArray(int size) {
            return new AddInvestmentTypeData[size];
        }
    };

    public String getInvestmentTypeName() {
        return investmentTypeName;
    }

    public void setInvestmentTypeName(String investmentTypeName) {
        this.investmentTypeName = investmentTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseTypeId() {
        return expenseTypeId;
    }

    public void setExpenseTypeId(String expenseTypeId) {
        this.expenseTypeId = expenseTypeId;
    }

    public int getExpenseTypePosition() {
        return expenseTypePosition;
    }

    public void setExpenseTypePosition(int expenseTypePosition) {
        this.expenseTypePosition = expenseTypePosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(investmentTypeName);
        dest.writeString(id);
        dest.writeString(status);
        dest.writeString(expenseType);
        dest.writeString(expenseTypeId);
        dest.writeInt(expenseTypePosition);
    }
}
