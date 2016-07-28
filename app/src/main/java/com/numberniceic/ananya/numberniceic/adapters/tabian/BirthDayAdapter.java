package com.numberniceic.ananya.numberniceic.adapters.tabian;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.numberniceic.ananya.numberniceic.pojo.tabain.BirthDay;
import com.numberniceic.ananya.numberniceic.views.tabian.BirthDayView;

import java.util.List;


public class BirthDayAdapter extends BaseAdapter{




    private List<BirthDay> birthDayList;

    public void setDayList(List<BirthDay> birthDays){

        this.birthDayList = birthDays;

    }

    @Override
    public int getCount() {
        return birthDayList.size();
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

        BirthDayView birthDayView;

        if (view != null){
            birthDayView = (BirthDayView) view;
        }else {
            birthDayView = new BirthDayView(viewGroup.getContext());
        }

        birthDayView.setDay(birthDayList.get(i).getThDay());


        return birthDayView;
    }
}
