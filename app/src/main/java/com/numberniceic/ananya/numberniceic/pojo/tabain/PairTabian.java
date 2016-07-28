package com.numberniceic.ananya.numberniceic.pojo.tabain;


import android.os.Parcel;
import android.os.Parcelable;

public class PairTabian  implements Parcelable{

    private String pair;
    private String type;
    private Integer point;

    public PairTabian(String pair, String type, Integer point) {
        this.pair = pair;
        this.type = type;
        this.point = point;
    }

    protected PairTabian(Parcel in) {
        pair = in.readString();
        type = in.readString();
    }

    public static final Creator<PairTabian> CREATOR = new Creator<PairTabian>() {
        @Override
        public PairTabian createFromParcel(Parcel in) {
            return new PairTabian(in);
        }

        @Override
        public PairTabian[] newArray(int size) {
            return new PairTabian[size];
        }
    };

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pair);
        parcel.writeString(type);
    }
}
