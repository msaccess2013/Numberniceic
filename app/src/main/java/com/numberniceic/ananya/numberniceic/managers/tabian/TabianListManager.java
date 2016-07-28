package com.numberniceic.ananya.numberniceic.managers.tabian;

import android.os.Parcel;
import android.os.Parcelable;

import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;

import java.util.List;



public class TabianListManager implements Parcelable{

    private List<PairTabian> listA;
    private List<PairTabian> listB;

    private String kn, sl, sm;

    public TabianListManager() {
    }

    protected TabianListManager(Parcel in) {
        listA = in.createTypedArrayList(PairTabian.CREATOR);
        listB = in.createTypedArrayList(PairTabian.CREATOR);
        kn = in.readString();
        sl = in.readString();
        sm = in.readString();
    }

    public static final Creator<TabianListManager> CREATOR = new Creator<TabianListManager>() {
        @Override
        public TabianListManager createFromParcel(Parcel in) {
            return new TabianListManager(in);
        }

        @Override
        public TabianListManager[] newArray(int size) {
            return new TabianListManager[size];
        }
    };

    public List<PairTabian> getListA() {
        return listA;
    }

    public void setListA(List<PairTabian> listA) {
        this.listA = listA;
    }

    public List<PairTabian> getListB() {
        return listB;
    }

    public void setListB(List<PairTabian> listB) {
        this.listB = listB;
    }

    public String getKn() {
        return kn;
    }

    public void setKn(String kn) {
        this.kn = kn;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(listA);
        parcel.writeTypedList(listB);
        parcel.writeString(kn);
        parcel.writeString(sl);
        parcel.writeString(sm);
    }
}
