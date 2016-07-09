package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by o_ye on 7/9/2016.
 */

public class LekSatItemCollectionDao implements Parcelable{

    private List<LekSatNameItemDao> lekSatNameItemDaos;
    private List<LekSatSurNameItemDao> lekSatSurNameItemDaos;
    private List<LekSatNickNameItemDao> lekSatNickNameItemDaos;

    public LekSatItemCollectionDao() {
    }

    protected LekSatItemCollectionDao(Parcel in) {
        lekSatNameItemDaos = in.createTypedArrayList(LekSatNameItemDao.CREATOR);
        lekSatSurNameItemDaos = in.createTypedArrayList(LekSatSurNameItemDao.CREATOR);
        lekSatNickNameItemDaos = in.createTypedArrayList(LekSatNickNameItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lekSatNameItemDaos);
        dest.writeTypedList(lekSatSurNameItemDaos);
        dest.writeTypedList(lekSatNickNameItemDaos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LekSatItemCollectionDao> CREATOR = new Creator<LekSatItemCollectionDao>() {
        @Override
        public LekSatItemCollectionDao createFromParcel(Parcel in) {
            return new LekSatItemCollectionDao(in);
        }

        @Override
        public LekSatItemCollectionDao[] newArray(int size) {
            return new LekSatItemCollectionDao[size];
        }
    };

    public List<LekSatNameItemDao> getLekSatNameItemDaos() {
        return lekSatNameItemDaos;
    }

    public void setLekSatNameItemDaos(List<LekSatNameItemDao> lekSatNameItemDaos) {
        this.lekSatNameItemDaos = lekSatNameItemDaos;
    }

    public List<LekSatSurNameItemDao> getLekSatSurNameItemDaos() {
        return lekSatSurNameItemDaos;
    }

    public void setLekSatSurNameItemDaos(List<LekSatSurNameItemDao> lekSatSurNameItemDaos) {
        this.lekSatSurNameItemDaos = lekSatSurNameItemDaos;
    }

    public List<LekSatNickNameItemDao> getLekSatNickNameItemDaos() {
        return lekSatNickNameItemDaos;
    }

    public void setLekSatNickNameItemDaos(List<LekSatNickNameItemDao> lekSatNickNameItemDaos) {
        this.lekSatNickNameItemDaos = lekSatNickNameItemDaos;
    }
}
