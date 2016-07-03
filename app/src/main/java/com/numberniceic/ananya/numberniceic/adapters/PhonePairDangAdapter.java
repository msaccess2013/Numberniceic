package com.numberniceic.ananya.numberniceic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.numberniceic.ananya.numberniceic.pojo.PairNumberDang;
import com.numberniceic.ananya.numberniceic.views.telephone.PairDang;

import java.util.List;

/**
 * Created by o_ye on 7/3/2016.
 */

public class PhonePairDangAdapter extends BaseAdapter{

    List<PairNumberDang> pairNumberDangs;

    public void setPairDang(List<PairNumberDang> pairDangList){

        pairNumberDangs = pairDangList;
    }

    @Override
    public int getCount() {

        if (pairNumberDangs == null)
            return 0;

        if (pairNumberDangs.size() == 0)
            return 0;

        return pairNumberDangs.size();
    }

    @Override
    public Object getItem(int i) {

        return pairNumberDangs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        PairDang pairDangItem = new PairDang(viewGroup.getContext());

        PairNumberDang dang = (PairNumberDang) getItem(i);
        pairDangItem.setDangTitle(dang.dangTitle);
        pairDangItem.setPairA(dang.pairNumberFirst);
        pairDangItem.setPairB(dang.pairNumberSecond);
        pairDangItem.setPoint(String.valueOf(dang.point));



        return pairDangItem;
    }
}
