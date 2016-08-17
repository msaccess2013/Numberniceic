package com.numberniceic.ananya.numberniceic.adapters.tabian;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.numberniceic.ananya.numberniceic.dao.tabian.TabianMiracleDao;
import com.numberniceic.ananya.numberniceic.managers.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabianMiracle;
import com.numberniceic.ananya.numberniceic.views.tabian.TabianMiracleDView;
import com.numberniceic.ananya.numberniceic.views.tabian.TabianMiracleRView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandland on 8/6/2016.
 */

public class TabianMiracleAdapter extends BaseAdapter {

    private List<PairTabianMiracle> miracles;


    public void setPairAdapter(List<PairTabianMiracle> listD, List<PairTabianMiracle> listR){

        miracles = new ArrayList<>();
        if (listD != null)
            miracles.addAll(listD);
        if (listR != null)
            miracles.addAll(listR);




    }

    @Override
    public int getCount() {

        int listCount = 0;

        if (miracles != null)
                listCount = miracles.size();

        return listCount;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        char t = miracles.get(position).getType().charAt(0);
        if (String.valueOf(t).equals("D"))
        return 0;
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TabianMiracleDView dView;
        TabianMiracleRView rView;

        if (getItemViewType(i) == 0){
            if (view != null)
                dView = (TabianMiracleDView) view;
                    dView = new TabianMiracleDView(viewGroup.getContext());

            dView.setTvPairD(miracles.get(i).getPair());
            dView.setTvDescription(miracles.get(i).getTile());
            dView.setTvDetial(miracles.get(i).getDescription());
            return dView;
        }

        if (getItemViewType(i) == 1){
            if (view != null)
                rView = (TabianMiracleRView) view;
            rView = new TabianMiracleRView(viewGroup.getContext());
            rView.setTvPairR(miracles.get(i).getPair());
            rView.setTvDescription(miracles.get(i).getTile());
            rView.setTvDetial(miracles.get(i).getDescription());
            return rView;
        }

        return null;
    }


}
