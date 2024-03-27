package com.example.vasool_drive.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class AddCustomerData implements Parcelable {
    private String LineName, id, LineId, CustomerName, CustomerCode, SubCode, CustomerOrder, MobileNo, AlternateMobile, SendSMS, Area, Status, Business, MaxLoanAmount, Address, IDNumber;

    public AddCustomerData() {

    }

    protected AddCustomerData(Parcel in) {
        LineName = in.readString();
        id = in.readString();
        LineId = in.readString();
        CustomerName = in.readString();
        CustomerCode = in.readString();
        SubCode = in.readString();
        CustomerOrder = in.readString();
        MobileNo = in.readString();
        AlternateMobile = in.readString();
        SendSMS = in.readString();
        Area = in.readString();
        Status = in.readString();
        Business = in.readString();
        MaxLoanAmount = in.readString();
        Address = in.readString();
        IDNumber = in.readString();
    }

    public static final Creator<AddCustomerData> CREATOR = new Creator<AddCustomerData>() {
        @Override
        public AddCustomerData createFromParcel(Parcel in) {
            return new AddCustomerData(in);
        }

        @Override
        public AddCustomerData[] newArray(int size) {
            return new AddCustomerData[size];
        }
    };

    public String getLineName() {
        return LineName;
    }

    public void setLineName(String lineName) {
        LineName = lineName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineId() {
        return LineId;
    }

    public void setLineId(String lineId) {
        LineId = lineId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getSubCode() {
        return SubCode;
    }

    public void setSubCode(String subCode) {
        SubCode = subCode;
    }

    public String getCustomerOrder() {
        return CustomerOrder;
    }

    public void setCustomerOrder(String customerOrder) {
        CustomerOrder = customerOrder;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAlternateMobile() {
        return AlternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        AlternateMobile = alternateMobile;
    }

    public String getSendSMS() {
        return SendSMS;
    }

    public void setSendSMS(String sendSMS) {
        SendSMS = sendSMS;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBusiness() {
        return Business;
    }

    public void setBusiness(String business) {
        Business = business;
    }

    public String getMaxLoanAmount() {
        return MaxLoanAmount;
    }

    public void setMaxLoanAmount(String maxLoanAmount) {
        MaxLoanAmount = maxLoanAmount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(LineName);
        dest.writeString(id);
        dest.writeString(LineId);
        dest.writeString(CustomerName);
        dest.writeString(CustomerCode);
        dest.writeString(SubCode);
        dest.writeString(CustomerOrder);
        dest.writeString(MobileNo);
        dest.writeString(AlternateMobile);
        dest.writeString(SendSMS);
        dest.writeString(Area);
        dest.writeString(Status);
        dest.writeString(Business);
        dest.writeString(MaxLoanAmount);
        dest.writeString(Address);
        dest.writeString(IDNumber);
    }



}
