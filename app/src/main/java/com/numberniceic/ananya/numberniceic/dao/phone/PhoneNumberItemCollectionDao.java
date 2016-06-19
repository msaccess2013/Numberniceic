package com.numberniceic.ananya.numberniceic.dao.phone;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 6/16/2016.
 */
public class PhoneNumberItemCollectionDao implements Parcelable {
    private PhoneNumberItemDao phoneNumberItemDaoSum;
    private List<PhoneNumberItemDao> phoneNumberItemDaosA = new ArrayList<>();
    private List<PhoneNumberItemDao> phoneNumberItemDaosB = new ArrayList<>();


    public PhoneNumberItemCollectionDao() {

    }

    protected PhoneNumberItemCollectionDao(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
