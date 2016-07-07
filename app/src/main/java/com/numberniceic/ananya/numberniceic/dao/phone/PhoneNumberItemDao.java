package com.numberniceic.ananya.numberniceic.dao.phone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 6/16/2016.
 */
public class PhoneNumberItemDao implements Parcelable{

    private String phoneNumber;
    private String type;
    private Integer point;

    public PhoneNumberItemDao(String phoneNumber, String type, Integer point) {
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.point = point;
    }

    public PhoneNumberItemDao() {
    }

    protected PhoneNumberItemDao(Parcel in) {
        phoneNumber = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhoneNumberItemDao> CREATOR = new Creator<PhoneNumberItemDao>() {
        @Override
        public PhoneNumberItemDao createFromParcel(Parcel in) {
            return new PhoneNumberItemDao(in);
        }

        @Override
        public PhoneNumberItemDao[] newArray(int size) {
            return new PhoneNumberItemDao[size];
        }
    };

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
