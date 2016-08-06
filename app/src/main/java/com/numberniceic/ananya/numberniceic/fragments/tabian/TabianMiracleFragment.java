package com.numberniceic.ananya.numberniceic.fragments.tabian;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.managers.tabian.TabianListManager;

public class TabianMiracleFragment extends Fragment {

    private TabianListManager manager;

    public static TabianMiracleFragment newInstance(TabianListManager manager) {

        Bundle args = new Bundle();
        args.putParcelable("TabianListManager", manager);

        TabianMiracleFragment fragment = new TabianMiracleFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            manager = getArguments().getParcelable("TabianListManager");

            Log.d("TabianListManager", String.valueOf(manager.getListA().size()));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabian_miracle, container, false);

        initInstance(rootView);

        return rootView;
    }

    private void initInstance(View rootView) {

    }


}
