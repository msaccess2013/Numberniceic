package com.numberniceic.ananya.numberniceic.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.fragments.tabian.TabianFragment;
import com.numberniceic.ananya.numberniceic.managers.tabian.TabianListManager;

public class MiracleTabianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miracle_tabian);

        Intent intent = getIntent();
        TabianListManager listManager = intent.getParcelableExtra("TabianListManager");
        Log.d("ListASize", String.valueOf(listManager.getListA().size()));
        for (int i = 0; i < listManager.getListA().size(); i++) {
            String pairA = listManager.getListA().get(i).getPair();
            Log.d("listA", pairA);

        }
    }
}
