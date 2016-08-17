package com.numberniceic.ananya.numberniceic.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.fragments.home.MiracleHomeFragment;

import java.util.ArrayList;

public class MiracleHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_miracle_home);
        if (savedInstanceState == null){
            ArrayList<String> pairHomeNumberList = getIntent().getStringArrayListExtra("pairHomeNumberList");
            getSupportFragmentManager().beginTransaction().replace(R.id.main_miracle_home_container,
                    MiracleHomeFragment.newInstance(pairHomeNumberList)).commit();
        }

        initInstance();
    }

    private void initInstance() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("ผลการทำนายเลขทะเบียนบ้าน");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
