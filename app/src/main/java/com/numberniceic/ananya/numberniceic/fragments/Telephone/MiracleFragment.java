package com.numberniceic.ananya.numberniceic.fragments.telephone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.MiracleAdapter;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneMiracleCollectionDao;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneMiracleItemDao;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.managers.telephone.PairNumberPercentManager;
import com.numberniceic.ananya.numberniceic.pojo.PairNumberPercent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiracleFragment extends Fragment {



    PhoneMiracleCollectionDao phoneMiracleCollectionDao = new PhoneMiracleCollectionDao();
    PhoneNumberItemCollectionDao dao;
    ListView phoneListView;
    MiracleAdapter miracleAdapter;

    List<PhoneMiracleItemDao> numberMiracleItemDaoList = new ArrayList<>();

    NumberPilotManager manager = new NumberPilotManager();
    NumberMiracleManager miracleManager = new NumberMiracleManager();


    public static MiracleFragment newInstance(PhoneNumberItemCollectionDao phoneNumberItemCollectionDao) {
        MiracleFragment miracleFragment = new MiracleFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("phoneCollectionDao", phoneNumberItemCollectionDao);
        miracleFragment.setArguments(bundle);
        return miracleFragment;
    }


    public MiracleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable("phoneNumberItemCollectionDao") != null) {

            dao = savedInstanceState.getParcelable("phoneNumberItemCollectionDao");


        }


        if (savedInstanceState == null) {

            dao = getArguments().getParcelable("phoneCollectionDao");

        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_miracle, container, false);
        initInstance(rootView);


        initMiracle();



        return rootView;
    }

    private void initMiracle() {

        List<String> pair = new ArrayList<>();

        if (dao != null) {

            for (int i = 0; i < dao.getPhoneNumberItemDaosA().size(); i++) {
                String pairNumber = dao.getPhoneNumberItemDaosA().get(i).getPhoneNumber();
                pair.add(pairNumber);

            }
            for (int i = 0; i < dao.getPhoneNumberItemDaosB().size(); i++) {
                String pairNumber = dao.getPhoneNumberItemDaosB().get(i).getPhoneNumber();
                pair.add(pairNumber);

            }


            pair.add(dao.getPhoneNumberItemDaoSum().getPhoneNumber());
        }


        //Add pair unique and count to hashMap
        Map<String, Integer> pairUnig = new HashMap<>();

        for (int i = 0; i < pair.size(); i++) {

            int count = Collections.frequency(pair, pair.get(i));

            pairUnig.put(pair.get(i), count);

        }


        //List all pair and percent : 100%
        List<PairNumberPercent> pairNumberPercents  = PairNumberPercentManager.getInstance().getPairNumberPercents();
        for (int i = 0; i < pairNumberPercents.size(); i++) {
            String goPair = pairNumberPercents.get(i).pairNumber;
            Integer goPercent = pairNumberPercents.get(i).percent;
            Log.d("pairPercent100", goPair + " : " + goPercent);
        }


        List<PairNumberPercent> percentList = new ArrayList<>();



        for (int i = 0; i < pairNumberPercents.size(); i++) {
            String myPairPercent = pairNumberPercents.get(i).pairNumber;
            Integer myPercentValue = pairNumberPercents.get(i).percent;


            if (pairUnig.containsKey(myPairPercent)) {

                percentList.add(new PairNumberPercent(myPairPercent, myPercentValue));

            }

        }




        List<PhoneMiracleItemDao> dPhoneMiracleItemDaos = new ArrayList<>();
        List<PhoneMiracleItemDao> rPhoneMiracleItemDaos = new ArrayList<>();

        Integer positionIndexR = 0;
        Integer positionIndexD = 0;

        for (Map.Entry<String, Integer> pp : pairUnig.entrySet()) {

            String pairUnix = pp.getKey();


            Integer sumP = 0;

            for (int i = 0; i < percentList.size(); i++) {

                if (pairUnix.equals(percentList.get(i).pairNumber)){

                    sumP = sumP + percentList.get(i).percent;
                }

            }


            String percent = String.valueOf(sumP);
            String mType = manager.getType(pairUnix);
            String mTitle = miracleManager.getTitle(pairUnix);
            String mDescription = miracleManager.getDescription(pairUnix);

            if (String.valueOf(mType.charAt(0)).equals("D") && positionIndexD == 0) {
                dPhoneMiracleItemDaos.add(new PhoneMiracleItemDao("00", "D", "00", "00", "00"));
                positionIndexD++;
            }

            if (String.valueOf(mType.charAt(0)).equals("R") && positionIndexR == 0) {
                rPhoneMiracleItemDaos.add(new PhoneMiracleItemDao("00", "R", "00", "00", "00"));
                positionIndexR++;
            }


            if (String.valueOf(mType.charAt(0)).equals("D")) {
                dPhoneMiracleItemDaos.add(new PhoneMiracleItemDao(pairUnix, mType, percent, mTitle, mDescription));
            }else {
                rPhoneMiracleItemDaos.add(new PhoneMiracleItemDao(pairUnix, mType, percent, mTitle, mDescription));
            }


        }

        numberMiracleItemDaoList.addAll(dPhoneMiracleItemDaos);
        numberMiracleItemDaoList.addAll(rPhoneMiracleItemDaos);
        phoneMiracleCollectionDao.setNumberMiracleItemDaos(numberMiracleItemDaoList);
        //pairNumberPercents.clear();
    }

    private void initInstance(View rootView) {
        phoneListView = (ListView) rootView.findViewById(R.id.lvMiraclePhone);
        miracleAdapter = new MiracleAdapter();

        miracleAdapter.setDao(phoneMiracleCollectionDao);

        phoneListView.setAdapter(miracleAdapter);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("MiracleLifeCycle", "onSaveInstanceState");
        outState.putParcelable("phoneNumberItemCollectionDao", dao);


    }
}
