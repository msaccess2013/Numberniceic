package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 7/9/2016.
 */

public class LekSatNickNameItemDao implements Parcelable {

    private char nickName;
    private String number;

    protected LekSatNickNameItemDao(Parcel in) {
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

    public static final Creator<LekSatNickNameItemDao> CREATOR = new Creator<LekSatNickNameItemDao>() {
        @Override
        public LekSatNickNameItemDao createFromParcel(Parcel in) {
            return new LekSatNickNameItemDao(in);
        }

        @Override
        public LekSatNickNameItemDao[] newArray(int size) {
            return new LekSatNickNameItemDao[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public char getNickName() {
        return nickName;
    }

    public LekSatNickNameItemDao() {

    }

    public LekSatNickNameItemDao(char nickName, String number) {

        this.nickName = nickName;
        this.number = number;
    }
}
