package com.numberniceic.ananya.numberniceic.adapters.home;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.managers.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.views.home.HomeMiracleDView;
import com.numberniceic.ananya.numberniceic.views.home.HomeMiracleRView;

import java.util.ArrayList;


public class MiracleHomeAdapter extends BaseAdapter {

    private ArrayList<String> pairHomeList;

    public void addHomeAdapter(ArrayList<String> pairHomeList) {
        if (pairHomeList != null) {
            this.pairHomeList = pairHomeList;

        }
    }


    @Override
    public int getCount() {
        int size = 0;
        if (pairHomeList != null) {
            if (pairHomeList.size() != 0) {
                size = pairHomeList.size();
                return size;
            }
        }
        return size;
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
        if (chkPair(pairHomeList.get(position)).equals("D"))
        return 0;
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeMiracleDView homeMiracleDView;
        HomeMiracleRView homeMiracleRView;

        if (getItemViewType(i) == 0){
            if (view != null){
                homeMiracleDView = (HomeMiracleDView) view;
            }else {
                homeMiracleDView = new HomeMiracleDView(viewGroup.getContext());
            }


            if (pairHomeList.get(i) != null){
                homeMiracleDView.setTvPairD(pairHomeList.get(i));
                NumberMiracleManager manager = new NumberMiracleManager();
                String title = manager.getTitle(pairHomeList.get(i));
                String description = manager.getDescription(pairHomeList.get(i));
                homeMiracleDView.setTvDescription(pairHomeList.get(i));

                homeMiracleDView.setTvDescription(title);
                homeMiracleDView.setTvDetial(description);
            }

            return homeMiracleDView;


        }
        if (getItemViewType(i) == 1){
            if (view != null){
                homeMiracleRView = (HomeMiracleRView) view;
            }else {
                homeMiracleRView = new HomeMiracleRView(viewGroup.getContext());
            }

            if (pairHomeList.get(i) != null){
                homeMiracleRView.setTvPairR(pairHomeList.get(i));
                NumberMiracleManager manager = new NumberMiracleManager();
                String title = manager.getTitle(pairHomeList.get(i));
                String description = manager.getDescription(pairHomeList.get(i));
                homeMiracleRView.setTvDescription(pairHomeList.get(i));

                homeMiracleRView.setTvDescription(title);
                homeMiracleRView.setTvDetial(description);
            }


            return homeMiracleRView;
        }



        return null;
    }





    private String chkPair(String pair) {
        String mType = null;

        NumberPilotManager manager = new NumberPilotManager();
        String type = manager.getType(pair);
        if (String.valueOf(type.charAt(0)).equals("D")) {
            mType = "D";

        }

        if (String.valueOf(type.charAt(0)).equals("R")) {
            mType = "R";
        }
        return mType;
    }
}
