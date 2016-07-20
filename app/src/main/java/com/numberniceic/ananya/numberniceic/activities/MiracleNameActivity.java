package com.numberniceic.ananya.numberniceic.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.dao.name.NameMiracleCollectionDao;
import com.numberniceic.ananya.numberniceic.fragments.name.NameMiracleFragment;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class MiracleNameActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miracle_name);

        if (savedInstanceState == null){
            NameMiracleCollectionDao nameMiracleDao = getIntent().getParcelableExtra("nameDao");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nameMiracleFragmentContainer,
                            NameMiracleFragment.newInstance(nameMiracleDao),"NameMiracleFragment").commit();
        }

        initInstance();

    }

    private void initInstance() {

        if (getSupportActionBar() != null) {

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setTitle("ผลการทำนายชื่อสกุล");
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
