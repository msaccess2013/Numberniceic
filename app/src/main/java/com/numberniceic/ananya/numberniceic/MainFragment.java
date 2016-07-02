package com.numberniceic.ananya.numberniceic;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.numberniceic.ananya.numberniceic.fragments.CarFragment;
import com.numberniceic.ananya.numberniceic.fragments.HomeFragment;
import com.numberniceic.ananya.numberniceic.fragments.NameFragment;
import com.numberniceic.ananya.numberniceic.fragments.Telephone.TelephoneFragment;


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

    public static class PairPhoneNumber extends FrameLayout {

        RelativeLayout rlPairNumber;
        TextView txtPairNumber;

        public PairPhoneNumber(Context context) {
            super(context);

            initInfate();
            initInstance();

        }

        public PairPhoneNumber(Context context, AttributeSet attrs) {
            super(context, attrs);
            initInfate();
            initInstance();
        }

        public PairPhoneNumber(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initInfate();
            initInstance();
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public PairPhoneNumber(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initInfate();
            initInstance();

        }

        private void initInstance() {
            rlPairNumber = (RelativeLayout) findViewById(R.id.rlPairNumber);
            txtPairNumber = (TextView) findViewById(R.id.txtPairNumber);

        }

        private void initInfate() {
                inflate(getContext(), R.layout.layout_pair_phone_number,this);
        }

        public void setBgColor(String color){
            rlPairNumber.setBackgroundColor(Color.parseColor(color));
        }
        public void setTxtPairNumber(String txt){
            txtPairNumber.setText(txt);
        }

    }

    public static class PairDang extends FrameLayout {

        private TextView pairA;
        private TextView pairB;



        public PairDang(Context context) {
            super(context);
            innitInflate();
            innitInstance();
        }

        public PairDang(Context context, AttributeSet attrs) {
            super(context, attrs);
            innitInflate();
            innitInstance();
        }

        public PairDang(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            innitInflate();
            innitInstance();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public PairDang(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            innitInflate();
            innitInstance();
        }

        private void innitInstance() {
                this.pairA = (TextView) findViewById(R.id.pairDangA);
                this.pairB = (TextView) findViewById(R.id.pairDangB);
        }

        private void innitInflate() {
            inflate(getContext(), R.layout.layout_pair_dang, this);
        }
    }
}



