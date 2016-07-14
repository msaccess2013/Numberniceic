package com.numberniceic.ananya.numberniceic.dao.name;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by o_ye on 7/14/2016.
 */

public class LekShaItemCollectionDao implements Parcelable{

    private List<LekShaNameDao> lekShaNameDaoList;
    private List<LekShaSurNameDao> lekShaSurNameDaoList;
    private List<LekShaNickNameDao> lekShaNickNameDaoList;

    public LekShaItemCollectionDao() {
    }

    public LekShaItemCollectionDao(List<LekShaNameDao> lekShaNameDaoList, List<LekShaSurNameDao> lekShaSurNameDaoList, List<LekShaNickNameDao> lekShaNickNameDaoList) {
        this.lekShaNameDaoList = lekShaNameDaoList;
        this.lekShaSurNameDaoList = lekShaSurNameDaoList;
        this.lekShaNickNameDaoList = lekShaNickNameDaoList;
    }

    protected LekShaItemCollectionDao(Parcel in) {
        lekShaNameDaoList = in.createTypedArrayList(LekShaNameDao.CREATOR);
        lekShaSurNameDaoList = in.createTypedArrayList(LekShaSurNameDao.CREATOR);
        lekShaNickNameDaoList = in.createTypedArrayList(LekShaNickNameDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lekShaNameDaoList);
        dest.writeTypedList(lekShaSurNameDaoList);
        dest.writeTypedList(lekShaNickNameDaoList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LekShaItemCollectionDao> CREATOR = new Creator<LekShaItemCollectionDao>() {
        @Override
        public LekShaItemCollectionDao createFromParcel(Parcel in) {
            return new LekShaItemCollectionDao(in);
        }

        @Override
        public LekShaItemCollectionDao[] newArray(int size) {
            return new LekShaItemCollectionDao[size];
        }
    };

    public List<LekShaNameDao> getLekShaNameDaoList() {
        return lekShaNameDaoList;
    }

    public void setLekShaNameDaoList(List<LekShaNameDao> lekShaNameDaoList) {
        this.lekShaNameDaoList = lekShaNameDaoList;
    }

    public List<LekShaSurNameDao> getLekShaSurNameDaoList() {
        return lekShaSurNameDaoList;
    }

    public void setLekShaSurNameDaoList(List<LekShaSurNameDao> lekShaSurNameDaoList) {
        this.lekShaSurNameDaoList = lekShaSurNameDaoList;
    }

    public List<LekShaNickNameDao> getLekShaNickNameDaoList() {
        return lekShaNickNameDaoList;
    }

    public void setLekShaNickNameDaoList(List<LekShaNickNameDao> lekShaNickNameDaoList) {
        this.lekShaNickNameDaoList = lekShaNickNameDaoList;
    }
}
