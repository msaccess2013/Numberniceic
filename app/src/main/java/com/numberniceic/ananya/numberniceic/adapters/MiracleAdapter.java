package com.numberniceic.ananya.numberniceic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.dao.phone.PhoneMiracleCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.dao.NumberMiracleCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.telephone.SummaryScrollManager;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleD;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleHead;


public class MiracleAdapter extends BaseAdapter {

    private Integer scrollD = SummaryScrollManager.getInstance().getScrollD();
    private Integer scrollR = SummaryScrollManager.getInstance().getScrollR();
    private String percentD = SummaryScrollManager.getInstance().getPercentD();
    private String percentR = SummaryScrollManager.getInstance().getPercentR();


    private PhoneMiracleCollectionDao dao;

    public void setDao(PhoneMiracleCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao.getNumberMiracleItemDaos() != null)
        return dao.getNumberMiracleItemDaos().size();
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

        MiracleHead miracleHead = new MiracleHead(viewGroup.getContext());
        MiracleD miracleD = new MiracleD(viewGroup.getContext());
        TextView tvNull = new TextView(viewGroup.getContext());



        if (i == 0) {

            miracleHead.setTvScrollD(String.valueOf(scrollD));
            miracleHead.setTvScrollR(String.valueOf(scrollR));
            miracleHead.setTvPercentD(percentD);
            miracleHead.setTvPercentR(percentR);

            return miracleHead;

        }


            if (String.valueOf(dao.getNumberMiracleItemDaos().get(i).getPairType().charAt(0)).equals("D")){

                miracleD.setPairNumber(dao.getNumberMiracleItemDaos().get(i).getPairNumber());
                miracleD.setDescription(dao.getNumberMiracleItemDaos().get(i).getMiracleTitle());
                return miracleD;
            }

        tvNull.setText(dao.getNumberMiracleItemDaos().get(i).getPairNumber());
        return tvNull;

    }
}
