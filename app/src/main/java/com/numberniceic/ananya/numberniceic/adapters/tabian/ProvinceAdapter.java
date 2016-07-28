package com.numberniceic.ananya.numberniceic.adapters.tabian;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.pojo.tabain.Province;
import com.numberniceic.ananya.numberniceic.views.tabian.ProviceView;

import java.util.List;

/**
 * Created by o_ye on 7/22/2016.
 */

public class ProvinceAdapter extends BaseAdapter {

    private ProviceView provinceView;
    private List<Province> provinces;


    public void addProvinceList(List<Province> provinceList){

        this.provinces = provinceList;

    }

    @Override
    public int getCount() {
        return provinces.size();
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



       if (view != null) {

           provinceView = (ProviceView) view;

       }else{
           provinceView = new ProviceView(viewGroup.getContext());
       }
        provinceView.setProvinceText(provinces.get(i).getProvinceName());

        return provinceView;
    }
}
