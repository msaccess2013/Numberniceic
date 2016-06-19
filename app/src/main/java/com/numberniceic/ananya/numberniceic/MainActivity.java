package com.numberniceic.ananya.numberniceic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.numberniceic.ananya.numberniceic.fragments.Telephone.MiracleFragment;
import com.numberniceic.ananya.numberniceic.fragments.Telephone.TelephoneFragment;

public class MainActivity extends AppCompatActivity implements TelephoneFragment.FragmentTelePhoneListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                    MainFragment.newInstance(), "MainFragment" ).commit();


        }


    }

    @Override
    public void onPairPhoneClick(String pairNumber) {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                MiracleFragment.newInstance(pairNumber),"MiracleFragment").addToBackStack(null).commit();

    }
}
