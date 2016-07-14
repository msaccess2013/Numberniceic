package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/14/2016.
 */

public class LekShaNickNameDao implements Parcelable {

    private char cName;
    private String cNumber;

    public LekShaNickNameDao(char cName, String cNumber) {
        this.cName = cName;
        this.cNumber = cNumber;
    }

    protected LekShaNickNameDao(Parcel in) {
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

    public static final Creator<LekShaNickNameDao> CREATOR = new Creator<LekShaNickNameDao>() {
        @Override
        public LekShaNickNameDao createFromParcel(Parcel in) {
            return new LekShaNickNameDao(in);
        }

        @Override
        public LekShaNickNameDao[] newArray(int size) {
            return new LekShaNickNameDao[size];
        }
    };

    public char getcName() {
        return cName;
    }

    public String getcNumber() {
        return cNumber;
    }
}
