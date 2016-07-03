package com.numberniceic.ananya.numberniceic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.numberniceic.ananya.numberniceic.managers.telephone.SummaryScrollManager;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleD;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleHead;


public class MiracleAdapter extends BaseAdapter {

    Integer scrollD = SummaryScrollManager.getInstance().getScrollD();
    Integer scrollR = SummaryScrollManager.getInstance().getScrollR();
    String percentD = SummaryScrollManager.getInstance().getPercentD();
    String percentR = SummaryScrollManager.getInstance().getPercentR();

    @Override
    public int getCount() {
        return 30;
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

        MiracleHead miracleHead = new MiracleHead(viewGroup.getContext());
        MiracleD miracleD = new MiracleD(viewGroup.getContext());

        if (i == 0) {

            miracleHead.setTvScrollD(String.valueOf(scrollD));
            miracleHead.setTvScrollR(String.valueOf(scrollR));
            miracleHead.setTvPercentD(percentD);
            miracleHead.setTvPercentR(percentR);

            return miracleHead;

        }

        return miracleD;
    }
}
