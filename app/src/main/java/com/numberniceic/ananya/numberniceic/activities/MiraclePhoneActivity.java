package com.numberniceic.ananya.numberniceic.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.numberniceic.ananya.numberniceic.R;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.fragments.telephone.MiracleFragment;

public class MiraclePhoneActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miracle_phone);

        initInstance();

        if (savedInstanceState == null) {

            Intent intent = getIntent();


            PhoneNumberItemCollectionDao phoneNumberItemCollectionDao = intent.getParcelableExtra("phoneNumberDao");

            if (phoneNumberItemCollectionDao != null) {

                getSupportFragmentManager().beginTransaction().replace(R.id.phoneMiracleContainer,
                        MiracleFragment.newInstance(phoneNumberItemCollectionDao), "MiracleFragment").commit();
            }
        }
    }

    private void initInstance() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setTitle("ผลทำนายเลขโทรศัพท์");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
