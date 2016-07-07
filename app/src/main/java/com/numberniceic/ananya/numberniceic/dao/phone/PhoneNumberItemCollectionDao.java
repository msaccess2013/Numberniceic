package com.numberniceic.ananya.numberniceic.dao.phone;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class PhoneNumberItemCollectionDao implements Parcelable {
    private PhoneNumberItemDao phoneNumberItemDaoSum;
    private List<PhoneNumberItemDao> phoneNumberItemDaosA;
    private List<PhoneNumberItemDao> phoneNumberItemDaosB;


    public PhoneNumberItemCollectionDao() {

    }

    protected PhoneNumberItemCollectionDao(Parcel in) {
        phoneNumberItemDaoSum = in.readParcelable(PhoneNumberItemDao.class.getClassLoader());
        phoneNumberItemDaosA = in.createTypedArrayList(PhoneNumberItemDao.CREATOR);
        phoneNumberItemDaosB = in.createTypedArrayList(PhoneNumberItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(phoneNumberItemDaoSum, flags);
        dest.writeTypedList(phoneNumberItemDaosA);
        dest.writeTypedList(phoneNumberItemDaosB);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhoneNumberItemCollectionDao> CREATOR = new Creator<PhoneNumberItemCollectionDao>() {
        @Override
        public PhoneNumberItemCollectionDao createFromParcel(Parcel in) {
            return new PhoneNumberItemCollectionDao(in);
        }

        @Override
        public PhoneNumberItemCollectionDao[] newArray(int size) {
            return new PhoneNumberItemCollectionDao[size];
        }
    };

    public PhoneNumberItemDao getPhoneNumberItemDaoSum() {
        return phoneNumberItemDaoSum;
    }

    public void setPhoneNumberItemDaoSum(PhoneNumberItemDao phoneNumberItemDaoSum) {
        this.phoneNumberItemDaoSum = phoneNumberItemDaoSum;
    }

    public List<PhoneNumberItemDao> getPhoneNumberItemDaosA() {
        return phoneNumberItemDaosA;
    }

    public void setPhoneNumberItemDaosA(List<PhoneNumberItemDao> phoneNumberItemDaosA) {
        this.phoneNumberItemDaosA = phoneNumberItemDaosA;
    }

    public List<PhoneNumberItemDao> getPhoneNumberItemDaosB() {
        return phoneNumberItemDaosB;
    }

    public void setPhoneNumberItemDaosB(List<PhoneNumberItemDao> phoneNumberItemDaosB) {
        this.phoneNumberItemDaosB = phoneNumberItemDaosB;
    }
}
