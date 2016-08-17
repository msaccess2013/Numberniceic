package com.numberniceic.ananya.numberniceic.dao.tabian;

import android.os.Parcel;
import android.os.Parcelable;

import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;

import java.util.List;

/**
 * Created by sandland on 8/6/2016.
 */

public class TabianMiracleDao implements Parcelable{
    private List<PairTabian> pairTabianList;

    public TabianMiracleDao() {
    }

    protected TabianMiracleDao(Parcel in) {
        pairTabianList = in.createTypedArrayList(PairTabian.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(pairTabianList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TabianMiracleDao> CREATOR = new Creator<TabianMiracleDao>() {
        @Override
        public TabianMiracleDao createFromParcel(Parcel in) {
            return new TabianMiracleDao(in);
        }

        @Override
        public TabianMiracleDao[] newArray(int size) {
            return new TabianMiracleDao[size];
        }
    };

    public List<PairTabian> getPairTabianList() {
        return pairTabianList;
    }

    public void setPairTabianList(List<PairTabian> pairTabianList) {
        this.pairTabianList = pairTabianList;
    }
}
