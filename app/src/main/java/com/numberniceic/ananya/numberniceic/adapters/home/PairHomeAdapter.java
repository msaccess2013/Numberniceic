package com.numberniceic.ananya.numberniceic.adapters.home;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;

import java.util.ArrayList;
import java.util.List;

public class PairHomeAdapter extends BaseAdapter {

    private List<String> pairHome;

    public void addPairHome(List<String> pairHome) {
        this.pairHome = pairHome;
    }

    @Override
    public int getCount() {
        if (pairHome != null)
            return pairHome.size();
        return 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView tvPairHome;
        String pair;

        if (view == null) {
            tvPairHome = new TextView(viewGroup.getContext());
        } else {
            tvPairHome = (TextView) view;
        }

        if (String.valueOf(pairHome.get(i).charAt(0)).equals("0")) {
            pair = String.valueOf(pairHome.get(i).charAt(1));
        } else {
            pair = String.valueOf(pairHome.get(i));
        }
        tvPairHome.setText(pair);

        if (chkPair(pairHome.get(i)).equals("D")) {
            tvPairHome.setBackgroundResource(R.drawable.bg_green);

        }

        if (chkPair(pairHome.get(i)).equals("R")) {
            tvPairHome.setBackgroundResource(R.drawable.bg_red);

        }


        tvPairHome.setPadding(50, 50, 50, 50);
        tvPairHome.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvPairHome.setTextAppearance(R.style.text_my_code);
        } else {
            tvPairHome.setTextAppearance(viewGroup.getContext(), R.style.text_my_code);
        }
        return tvPairHome;
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
