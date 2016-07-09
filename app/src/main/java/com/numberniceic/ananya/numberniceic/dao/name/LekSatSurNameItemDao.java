package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/9/2016.
 */

public class LekSatSurNameItemDao implements Parcelable{

    private char surName;
    private String number;

    public LekSatSurNameItemDao(char surName, String number) {
        this.surName = surName;
        this.number = number;
    }

    protected LekSatSurNameItemDao(Parcel in) {
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

    public static final Creator<LekSatSurNameItemDao> CREATOR = new Creator<LekSatSurNameItemDao>() {
        @Override
        public LekSatSurNameItemDao createFromParcel(Parcel in) {
            return new LekSatSurNameItemDao(in);
        }

        @Override
        public LekSatSurNameItemDao[] newArray(int size) {
            return new LekSatSurNameItemDao[size];
        }
    };

    public char getSurName() {
        return surName;
    }

    public String getNumber() {
        return number;
    }

    public LekSatSurNameItemDao() {

    }
}
