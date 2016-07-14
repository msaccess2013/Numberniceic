package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/14/2016.
 */

public class LekShaNameDao implements Parcelable {

    private char cName;
    private String cNumber;

    public LekShaNameDao(char cName, String cNumber) {
        this.cName = cName;
        this.cNumber = cNumber;
    }

    protected LekShaNameDao(Parcel in) {
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

    public static final Creator<LekShaNameDao> CREATOR = new Creator<LekShaNameDao>() {
        @Override
        public LekShaNameDao createFromParcel(Parcel in) {
            return new LekShaNameDao(in);
        }

        @Override
        public LekShaNameDao[] newArray(int size) {
            return new LekShaNameDao[size];
        }
    };

    public char getcName() {
        return cName;
    }

    public String getcNumber() {
        return cNumber;
    }
}
