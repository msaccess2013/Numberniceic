package com.numberniceic.ananya.numberniceic.fragments.Telephone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.MiracleAdapter;
import com.numberniceic.ananya.numberniceic.adapters.PhonePairDangAdapter;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.telephone.SummaryScrollManager;
import com.numberniceic.ananya.numberniceic.pojo.PairNumberCount;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiracleFragment extends Fragment {


    ListView phoneListView;
    MiracleAdapter miracleAdapter;




    public static MiracleFragment newInstance(PhoneNumberItemCollectionDao phoneNumberItemCollectionDao) {
        MiracleFragment miracleFragment = new MiracleFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("pairNumber", phoneNumberItemCollectionDao);
        miracleFragment.setArguments(bundle);
        return miracleFragment;
    }


    public MiracleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

            PhoneNumberItemCollectionDao dao = getArguments().getParcelable("pairNumber");

            List<String> pair = new ArrayList<>();


            if (dao != null) {
                Toast.makeText(getContext(), "pairNumber : " + dao.getPhoneNumberItemDaosA().get(0).getPhoneNumber(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
                    String pairNumber = dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber();
                    pair.add(pairNumber);

                    Log.d("pairNumber", pairNumber);
                }
            }

            HashSet<String> hPair = new HashSet<>();
            hPair.addAll(pair);

            for (String vPair : hPair) {
                Log.d("vPair", vPair);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_miracle, container, false);
        initInstance(rootView);

        return rootView;
    }

    private void initInstance(View rootView) {
        phoneListView = (ListView) rootView.findViewById(R.id.lvMiraclePhone);
        miracleAdapter = new MiracleAdapter();

        phoneListView.setAdapter(miracleAdapter);


    }

}
