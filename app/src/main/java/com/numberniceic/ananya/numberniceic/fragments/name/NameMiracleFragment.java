package com.numberniceic.ananya.numberniceic.fragments.name;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.Name.NameMiracleAdapter;
import com.numberniceic.ananya.numberniceic.dao.name.NameMiracleCollectionDao;
import com.numberniceic.ananya.numberniceic.managers.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.managers.NumberPilotManager;
import com.numberniceic.ananya.numberniceic.pojo.name.NamePairMiracle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameMiracleFragment extends Fragment {


    private NameMiracleCollectionDao dao;
    private Set<String> pairUnique;
    private List<NamePairMiracle> pairDList;
    private List<NamePairMiracle> pairRList;
    private List<NamePairMiracle> pairDRList;

    private ListView lvNameMiracle;



    public static NameMiracleFragment newInstance(NameMiracleCollectionDao pairDao) {

        Bundle args = new Bundle();
        args.putParcelable("pairDao", pairDao);

        NameMiracleFragment fragment = new NameMiracleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public NameMiracleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            dao = getArguments().getParcelable("pairDao");
        }

        if (savedInstanceState != null){

            dao = savedInstanceState.getParcelable("myDao");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_name_miracle, container, false);
        initInstance(rootView);


        // pair unique filter
        if (dao != null) {
             pairUnique = new HashSet<>();
            for (int i = 0; i < dao.getUnigueNumber().size(); i++) {
                String myPair = dao.getUnigueNumber().get(i);
                pairUnique.add(myPair);
            }
        }

        pairDRList = new ArrayList<>();
        findDtoList();
        findRtoList();

        NameMiracleAdapter nameMiracleAdapter = new NameMiracleAdapter();
        nameMiracleAdapter.addList(pairDRList);
        lvNameMiracle.setAdapter(nameMiracleAdapter);



        return rootView;
    }

    private void findDtoList() {

        if (pairDList == null) {
            pairDList = new ArrayList<>();
            NumberMiracleManager miracleManager = new NumberMiracleManager();

            if (pairUnique != null) {
                for (String mPairUnique : pairUnique) {
                    if (mPairUnique.length() == 1 && !mPairUnique.equals("0")){
                        mPairUnique = "0" + mPairUnique;
                    }
                    String pairType = miracleManager.getType(mPairUnique);

                    if (pairType != null && pairType.equals("D")) {

                        String miracle = miracleManager.getTitle(mPairUnique);
                        String detial = miracleManager.getDescription(mPairUnique);
                        String type = miracleManager.getType(mPairUnique);
                        pairDList.add(new NamePairMiracle(mPairUnique, miracle, detial, type));
                    }
                }

                pairDRList.addAll(pairDList);

            }
        }

    }

    private void findRtoList() {

        if (pairRList == null) {
            pairRList = new ArrayList<>();
            NumberMiracleManager miracleManager = new NumberMiracleManager();

            if (pairUnique != null) {
                for (String mPairUnique : pairUnique) {
                    if (mPairUnique.length() == 1 && !mPairUnique.equals("0")){
                        mPairUnique = "0" + mPairUnique;
                    }
                    String pairType = miracleManager.getType(mPairUnique);

                    if (pairType != null && pairType.equals("R")) {

                        String miracle = miracleManager.getTitle(mPairUnique);
                        String detial = miracleManager.getDescription(mPairUnique);
                        String type = miracleManager.getType(mPairUnique);

                        pairRList.add(new NamePairMiracle(mPairUnique, miracle, detial, type));
                    }
                }

                pairDRList.addAll(pairRList);

            }
        }

    }

    private void initInstance(View rootView) {

        lvNameMiracle = (ListView) rootView.findViewById(R.id.lvNameMiracle);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("myDao", dao);
    }
}
