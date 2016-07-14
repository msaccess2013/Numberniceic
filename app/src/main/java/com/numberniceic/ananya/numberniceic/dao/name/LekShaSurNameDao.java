package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/14/2016.
 */

public class LekShaSurNameDao implements Parcelable {

    private char cName;
    private String cNumber;

    public LekShaSurNameDao(char cName, String cNumber) {
        this.cName = cName;
        this.cNumber = cNumber;
    }

    protected LekShaSurNameDao(Parcel in) {
        cNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LekShaSurNameDao> CREATOR = new Creator<LekShaSurNameDao>() {
        @Override
        public LekShaSurNameDao createFromParcel(Parcel in) {
            return new LekShaSurNameDao(in);
        }

        @Override
        public LekShaSurNameDao[] newArray(int size) {
            return new LekShaSurNameDao[size];
        }
    };

    public char getcName() {
        return cName;
    }

    public String getcNumber() {
        return cNumber;
    }
}
