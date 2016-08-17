package com.numberniceic.ananya.numberniceic.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.dao.tabian.TabianMiracleDao;
import com.numberniceic.ananya.numberniceic.fragments.tabian.TabianFragment;
import com.numberniceic.ananya.numberniceic.fragments.tabian.TabianMiracleFragment;
import com.numberniceic.ananya.numberniceic.managers.tabian.TabianListManager;

public class MiracleTabianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_miracle_tabian);
        Intent intent = getIntent();
        TabianMiracleDao dao = intent.getParcelableExtra("dao");

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.tabianMiracleContainer,
                    TabianMiracleFragment.newInstance(dao)).commit();
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

