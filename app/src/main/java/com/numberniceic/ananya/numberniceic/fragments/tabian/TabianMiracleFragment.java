package com.numberniceic.ananya.numberniceic.fragments.tabian;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.tabian.TabianMiracleAdapter;
import com.numberniceic.ananya.numberniceic.dao.tabian.TabianMiracleDao;
import com.numberniceic.ananya.numberniceic.managers.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.managers.tabian.TabianListManager;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabian;
import com.numberniceic.ananya.numberniceic.pojo.tabain.PairTabianMiracle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TabianMiracleFragment extends Fragment {


    private ListView listView;
    private List<PairTabianMiracle> listD, listR;

    private HashSet<String> pairUnique;

    public static TabianMiracleFragment newInstance(TabianMiracleDao dao) {

        Bundle args = new Bundle();
        args.putParcelable("dao", dao);

        TabianMiracleFragment fragment = new TabianMiracleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        TabianMiracleDao dao;  dao = getArguments().getParcelable("dao");


            // pair unique filter
            if (dao != null) {
                pairUnique = new HashSet<>();
                for (int i = 0; i < dao.getPairTabianList().size(); i++) {
                    String myPair = dao.getPairTabianList().get(i).getPair();
                    if (myPair != null)
                        pairUnique.add(myPair);
                }
            }

            if (dao != null) {
                listD = new ArrayList<>();
                listR = new ArrayList<>();
                NumberMiracleManager manager = new NumberMiracleManager();

                for (String mPairUnigue : pairUnique) {

                    String mType = manager.getType(mPairUnigue);
                    String mTitle = manager.getTitle(mPairUnigue);
                    String mDescript = manager.getDescription(mPairUnigue);

                    if (String.valueOf(mPairUnigue.charAt(0)).equals("0")){
                        mPairUnigue = String.valueOf(mPairUnigue.charAt(1));
                    }

                    char cType = mType.charAt(0);

                    if (String.valueOf(cType).equals("D")) {
                        listD.add(new PairTabianMiracle(mPairUnigue, mType, mTitle, mDescript));
                    }
                    if (String.valueOf(cType).equals("R")) {
                        listR.add(new PairTabianMiracle(mPairUnigue, mType, mTitle, mDescript));
                    }

                }
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabian_miracle, container, false);

        initInstance(rootView);
        TabianMiracleAdapter adapter = new TabianMiracleAdapter();
        adapter.setPairAdapter(listD, listR);
        listView.setAdapter(adapter);

        return rootView;
    }

    private void initInstance(View rootView) {

        listView = (ListView) rootView.findViewById(R.id.lvTabianMiracle);


    }


}
