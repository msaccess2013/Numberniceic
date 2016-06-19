package com.numberniceic.ananya.numberniceic.dao.phone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by o_ye on 6/18/2016.
 */

public class ScrollPairNumberDao implements Parcelable{

    private Integer pairsAD, pairsBD, pairsAR, pairsBR;
    private Integer pairSumD, pairSumR;
    private Integer continueD1x, continueD2x, continueD3x, continueD4x, continueD5x;

    public ScrollPairNumberDao(Parcel in) {
    }

    public ScrollPairNumberDao() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScrollPairNumberDao> CREATOR = new Creator<ScrollPairNumberDao>() {
        @Override
        public ScrollPairNumberDao createFromParcel(Parcel in) {
            return new ScrollPairNumberDao(in);
        }

        @Override
        public ScrollPairNumberDao[] newArray(int size) {
            return new ScrollPairNumberDao[size];
        }
    };

    public Integer getPairsAD() {
        return pairsAD;
    }

    public void setPairsAD(Integer pairsAD) {
        this.pairsAD = pairsAD;
    }

    public Integer getPairsBD() {
        return pairsBD;
    }

    public void setPairsBD(Integer pairsBD) {
        this.pairsBD = pairsBD;
    }

    public Integer getPairsAR() {
        return pairsAR;
    }

    public void setPairsAR(Integer pairsAR) {
        this.pairsAR = pairsAR;
    }

    public Integer getPairsBR() {
        return pairsBR;
    }

    public void setPairsBR(Integer pairsBR) {
        this.pairsBR = pairsBR;
    }

    public Integer getPairSumD() {
        return pairSumD;
    }

    public void setPairSumD(Integer pairSumD) {
        this.pairSumD = pairSumD;
    }

    public Integer getPairSumR() {
        return pairSumR;
    }

    public void setPairSumR(Integer pairSumR) {
        this.pairSumR = pairSumR;
    }

    public Integer getContinueD1x() {
        return continueD1x;
    }

    public void setContinueD1x(Integer continueD1x) {
        this.continueD1x = continueD1x;
    }

    public Integer getContinueD2x() {
        return continueD2x;
    }

    public void setContinueD2x(Integer continueD2x) {
        this.continueD2x = continueD2x;
    }

    public Integer getContinueD3x() {
        return continueD3x;
    }

    public void setContinueD3x(Integer continueD3x) {
        this.continueD3x = continueD3x;
    }

    public Integer getContinueD4x() {
        return continueD4x;
    }

    public void setContinueD4x(Integer continueD4x) {
        this.continueD4x = continueD4x;
    }

    public Integer getContinueD5x() {
        return continueD5x;
    }

    public void setContinueD5x(Integer continueD5x) {
        this.continueD5x = continueD5x;
    }
}
