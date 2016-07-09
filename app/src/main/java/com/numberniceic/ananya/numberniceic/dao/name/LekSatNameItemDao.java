package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/9/2016.
 */

public class LekSatNameItemDao implements Parcelable{

    private char name;
    private String number;


    public LekSatNameItemDao() {
    }

    public LekSatNameItemDao(String number, char name) {
        this.number = number;
        this.name = name;
    }

    protected LekSatNameItemDao(Parcel in) {
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LekSatNameItemDao> CREATOR = new Creator<LekSatNameItemDao>() {
        @Override
        public LekSatNameItemDao createFromParcel(Parcel in) {
            return new LekSatNameItemDao(in);
        }

        @Override
        public LekSatNameItemDao[] newArray(int size) {
            return new LekSatNameItemDao[size];
        }
    };

    public char getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
