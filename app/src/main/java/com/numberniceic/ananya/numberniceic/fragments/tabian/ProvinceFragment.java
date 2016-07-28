package com.numberniceic.ananya.numberniceic.fragments.tabian;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.tabian.ProvinceAdapter;
import com.numberniceic.ananya.numberniceic.dao.tabian.ProvinceCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.eventbus.EventBus;
import com.numberniceic.ananya.numberniceic.managers.eventbus.FragMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvinceFragment extends DialogFragment {

    private ProvinceCollectionDao dao;

    public ProvinceFragment() {
        // Required empty public constructor
    }



    public interface OnProvinceClickListener{
        void onProvinceClick(String province);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_province, container, false);

        initInstance(rootView);

        return rootView;
    }

    private void initInstance(View rootView) {

        getDialog().setTitle("เลือกจังหวัด");

        dao = new ProvinceCollectionDao();


        ListView lvProvince = (ListView) rootView.findViewById(R.id.lvProvince);
        ProvinceAdapter adapter = new ProvinceAdapter();

        adapter.addProvinceList(dao.getProvinceList());

        lvProvince.setAdapter(adapter);
        lvProvince.setOnItemClickListener(click);



    }



    private AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String province = dao.getProvinceList().get(i).getProvinceName();
                       OnProvinceClickListener onProvinceClickListener = (OnProvinceClickListener) getActivity();
            onProvinceClickListener.onProvinceClick(province);
            EventBus.getInstance().post(new FragMessage(province));
            getDialog().cancel();
        }
    };

}
