package com.numberniceic.ananya.numberniceic.fragments.Telephone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiracleFragment extends Fragment {

    public static MiracleFragment newInstance(String pairNumber){
        MiracleFragment miracleFragment = new MiracleFragment();

        Bundle bundle = new Bundle();
        bundle.putString("pairNumber", pairNumber);
        miracleFragment.setArguments(bundle);
        return miracleFragment;
    }


    public MiracleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){

            String pairNumber = getArguments().getString("pairNumber");

            Toast.makeText(getContext(), "pairNumber : " + pairNumber, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_miracle, container, false);
    }

}
