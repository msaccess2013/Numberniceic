package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_ye on 7/16/2016.
 */

public class NameMiracleCollectionDao implements Parcelable {

    private List<String> unigueNumber = new ArrayList<>();

    public NameMiracleCollectionDao(List<String> unigueNumber) {
        this.unigueNumber = unigueNumber;
    }

    public NameMiracleCollectionDao() {
    }

    protected NameMiracleCollectionDao(Parcel in) {
        unigueNumber = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(unigueNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NameMiracleCollectionDao> CREATOR = new Creator<NameMiracleCollectionDao>() {
        @Override
        public NameMiracleCollectionDao createFromParcel(Parcel in) {
            return new NameMiracleCollectionDao(in);
        }

        @Override
        public NameMiracleCollectionDao[] newArray(int size) {
            return new NameMiracleCollectionDao[size];
        }
    };

    public List<String> getUnigueNumber() {
        return unigueNumber;
    }

    public void setUnigueNumber(List<String> unigueNumber) {
        this.unigueNumber = unigueNumber;
    }
}
