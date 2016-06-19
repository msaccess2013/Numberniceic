package com.numberniceic.ananya.numberniceic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.fragments.TelephoneFragment;
import com.numberniceic.ananya.numberniceic.managers.telephone.NumberMiracleManager;
import com.numberniceic.ananya.numberniceic.managers.telephone.NumberPilotManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                    MainFragment.newInstance(), "MainFragment" ).commit();
        }


    }
}
