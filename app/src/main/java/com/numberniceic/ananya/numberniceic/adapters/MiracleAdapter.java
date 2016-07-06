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
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleR;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleTitleD;
import com.numberniceic.ananya.numberniceic.views.miracle.MiracleTitleR;


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
            return dao.getNumberMiracleItemDaos().size() + 1;
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
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return 0;


        if (String.valueOf(dao.getNumberMiracleItemDaos().get(position - 1).getPairType().charAt(0)).equals("D") &&
                dao.getNumberMiracleItemDaos().get(position - 1).getPairNumber().equals("00")) {

            return 3;

        }else if (String.valueOf(dao.getNumberMiracleItemDaos().get(position - 1).getPairType().charAt(0)).equals("D")) {

            return 1;

        }

        if (String.valueOf(dao.getNumberMiracleItemDaos().get(position - 1).getPairType().charAt(0)).equals("R") &&
                dao.getNumberMiracleItemDaos().get(position - 1).getPairNumber().equals("00")) {

            return 4;

        }else if (String.valueOf(dao.getNumberMiracleItemDaos().get(position - 1).getPairType().charAt(0)).equals("R")) {

            return 2;
        }


        return 0;

    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MiracleHead miracleHead;
        MiracleD miracleD;
        MiracleR miracleR;

        MiracleTitleD miracleTitleD;
        MiracleTitleR miracleTitleR;


        if (getItemViewType(i) == 0) {

            if (view != null) {
                miracleHead = (MiracleHead) view;
            } else {
                miracleHead = new MiracleHead(viewGroup.getContext());
            }

            miracleHead.setTvScrollD(String.valueOf(scrollD));
            miracleHead.setTvScrollR(String.valueOf(scrollR));
            miracleHead.setTvPercentD(percentD);
            miracleHead.setTvPercentR(percentR);
            return miracleHead;
        }

        if (getItemViewType(i) == 1) {
            if (view != null) {
                miracleD = (MiracleD) view;
            } else {
                miracleD = new MiracleD(viewGroup.getContext());
            }
            miracleD.setPairNumber(dao.getNumberMiracleItemDaos().get(i - 1).getPairNumber());
            miracleD.setDescription(dao.getNumberMiracleItemDaos().get(i - 1).getMiracleTitle());
            miracleD.setPercent(dao.getNumberMiracleItemDaos().get(i - 1).getPairPercent());
            miracleD.setDetial(dao.getNumberMiracleItemDaos().get(i - 1).getMiracleDescription());
            return miracleD;

        }


        if (getItemViewType(i) == 2) {
            if (view != null) {
                miracleR = (MiracleR) view;
            } else {
                miracleR = new MiracleR(viewGroup.getContext());
            }

            miracleR.setPairNumber(dao.getNumberMiracleItemDaos().get(i - 1).getPairNumber());
            miracleR.setDescription(dao.getNumberMiracleItemDaos().get(i - 1).getMiracleTitle());
            miracleR.setPercent(dao.getNumberMiracleItemDaos().get(i - 1).getPairPercent());
            miracleR.setDetial(dao.getNumberMiracleItemDaos().get(i - 1).getMiracleDescription());
            return miracleR;
        }

        if (getItemViewType(i) == 3) {

            if (view != null) {
                miracleTitleD = (MiracleTitleD) view;
            } else {
                miracleTitleD = new MiracleTitleD(viewGroup.getContext());
            }

            return miracleTitleD;
        }

        if (getItemViewType(i) == 4) {

            if (view != null) {
                miracleTitleR = (MiracleTitleR) view;
            } else {
                miracleTitleR = new MiracleTitleR(viewGroup.getContext());
            }

            return miracleTitleR;
        }

        return new MiracleR(viewGroup.getContext());
    }
}
