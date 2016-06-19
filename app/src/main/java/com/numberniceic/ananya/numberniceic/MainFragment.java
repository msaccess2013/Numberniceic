package com.numberniceic.ananya.numberniceic;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberniceic.ananya.numberniceic.fragments.CarFragment;
import com.numberniceic.ananya.numberniceic.fragments.HomeFragment;
import com.numberniceic.ananya.numberniceic.fragments.NameFragment;
import com.numberniceic.ananya.numberniceic.fragments.TelephoneFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    public static MainFragment newInstance(){

        MainFragment mainFragment = new MainFragment();
        //Bundle bundle = new Bundle();
        //bundle.putString();
        //mainFragment.setArguments();

        return mainFragment;
    }


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initInstance(rootView);

        return rootView;
    }

    private void initInstance(View rootView) {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0: return TelephoneFragment.newInstance();
                    case 1: return NameFragment.newInstance();
                    case 2: return CarFragment.newInstance();
                    case 3: return HomeFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "เบอร์โทร";
                    case 1:
                        return "ชื่อสกุล";
                    case 2:
                        return "ทะเบียนรถ";
                    case 3:
                        return "บ้านเลขที่";
                    default:
                        return null;
                }

            }
        });

        tabLayout = (TabLayout) rootView.findViewById(R.id.myTabLayout);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }


    }
    }



