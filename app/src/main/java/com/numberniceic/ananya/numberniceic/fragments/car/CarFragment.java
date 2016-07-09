package com.numberniceic.ananya.numberniceic.fragments.car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberniceic.ananya.numberniceic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment {


    public static CarFragment newInstance(){

        CarFragment carFragment = new CarFragment();
        return carFragment;

    }

    public CarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

}
