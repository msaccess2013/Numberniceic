package com.numberniceic.ananya.numberniceic.adapters.Name;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.numberniceic.ananya.numberniceic.pojo.name.NamePairMiracle;
import com.numberniceic.ananya.numberniceic.views.name.NameMiracleDView;
import com.numberniceic.ananya.numberniceic.views.name.NameMiracleRView;

import java.util.List;

/**
 * Created by o_ye on 7/18/2016.
 */

public class NameMiracleAdapter extends BaseAdapter {

    private List<NamePairMiracle> pairList;

    public void addList(List<NamePairMiracle> pairList) {

        if (pairList != null) {
            this.pairList = pairList;
        }

    }

    @Override
    public int getCount() {
        return pairList.size();
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


        if (pairList.get(position).getType().equals("D")){

            return 0;
        }else {
            return 1;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        NameMiracleDView dView;
        NameMiracleRView rView;

        if (getItemViewType(i) == 0) {

            if (view != null) {
                dView = (NameMiracleDView) view;
            } else {
                dView = new NameMiracleDView(viewGroup.getContext());
            }

            dView.setTvPairD(pairList.get(i).getPair());
            dView.setTvDescription(pairList.get(i).getDescription());
            dView.setTvDetial(pairList.get(i).getDetial());

            return dView;
        }

        if (getItemViewType(i) == 1) {

            if (view != null) {
                rView = (NameMiracleRView) view;
            } else {
                rView = new NameMiracleRView(viewGroup.getContext());
            }

            rView.setTvPairR(pairList.get(i).getPair());
            rView.setTvDescription(pairList.get(i).getDescription());
            rView.setTvDetial(pairList.get(i).getDetial());

            return rView;
        }


        return view;


    }
}
