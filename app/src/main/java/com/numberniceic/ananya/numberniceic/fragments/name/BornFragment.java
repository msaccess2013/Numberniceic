package com.numberniceic.ananya.numberniceic.fragments.name;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.numberniceic.ananya.numberniceic.MainActivity;
import com.numberniceic.ananya.numberniceic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BornFragment extends Fragment {

    private NameFragment nameFragment;
    private RadioGroup rgDay;

    public BornFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_born, container, false);

        nameFragment = (NameFragment) getParentFragment();


        initInstance(rootView);


        rgDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (rgDay.getCheckedRadioButtonId()){

                    case R.id.rbSunday :
                        nameFragment.setBirth("Sunday");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbMonday :

                        nameFragment.setBirth("Monday");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbTuesday :
                        nameFragment.setBirth("Tuesday");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbWednesdayAm :
                        nameFragment.setBirth("WednesdayAm");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbWednesdayPm :
                        nameFragment.setBirth("WednesdayPm");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbThursday :
                        nameFragment.setBirth("Thursday");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbFriday :
                        nameFragment.setBirth("Friday");
                        getFragmentManager().popBackStack();
                        break;
                    case R.id.rbSaturday :
                        nameFragment.setBirth("Saturday");
                        getFragmentManager().popBackStack();
                        break;


                }
            }
        });



        return rootView;
    }



    private void initInstance(View rootView) {

        rgDay = (RadioGroup) rootView.findViewById(R.id.rgDay);







    }

}
