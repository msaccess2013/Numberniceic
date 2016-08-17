package com.numberniceic.ananya.numberniceic.fragments.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.adapters.home.MiracleHomeAdapter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiracleHomeFragment extends Fragment {


    private ArrayList<String> pairHomeNumberList;
    private ListView lvHomeMiracle;

    public static MiracleHomeFragment newInstance(ArrayList<String> pairHomeNumberList) {

        Bundle args = new Bundle();
        if (pairHomeNumberList != null) {
                args.putStringArrayList("pairHomeNumberList", pairHomeNumberList);
        }

        MiracleHomeFragment fragment = new MiracleHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public MiracleHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView =  inflater.inflate(R.layout.fragment_miracle_home, container, false);


        initInstance(rootView);
            MiracleHomeAdapter adapter = new MiracleHomeAdapter();
                adapter.addHomeAdapter(pairHomeNumberList);
                lvHomeMiracle.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (pairHomeNumberList != null){
            outState.putStringArrayList("pairHomeNumberList", pairHomeNumberList);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            pairHomeNumberList = getArguments().getStringArrayList("pairHomeNumberList");

        }

        if (savedInstanceState != null){
            pairHomeNumberList = savedInstanceState.getStringArrayList("pairHomeNumberList");
        }
    }


    private void initInstance(View rootView) {
        lvHomeMiracle = (ListView) rootView.findViewById(R.id.lvMiracleHome);

    }

}
