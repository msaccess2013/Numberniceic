package com.numberniceic.ananya.numberniceic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberniceic.ananya.numberniceic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {


    public static NameFragment newInstance(){

        NameFragment nameFragment = new NameFragment();
        return nameFragment;

    }

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

}
