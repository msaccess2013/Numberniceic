package com.numberniceic.ananya.numberniceic;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.activities.MiraclePhoneActivity;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.fragments.Telephone.MiracleFragment;
import com.numberniceic.ananya.numberniceic.fragments.Telephone.TelephoneFragment;

public class MainActivity extends AppCompatActivity implements TelephoneFragment.FragmentTelePhoneListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                    MainFragment.newInstance(), "MainFragment").commit();


        }

        initInstance();


    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, R.string.drawer_open,R.string.drawer_close);
                drawerLayout.setDrawerElevation(0);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPairPhoneClick(PhoneNumberItemCollectionDao phoneNumberItemCollectionDao) {


/*
       getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                MiracleFragment.newInstance(phoneNumberItemCollectionDao), "MiracleFragment").addToBackStack(null).commit();
*/

        for (int i = 0; i < phoneNumberItemCollectionDao.getPhoneNumberItemDaosA().size(); i++) {
            Log.d("phoneNumberDao", phoneNumberItemCollectionDao.getPhoneNumberItemDaosA().get(i).getPhoneNumber());
        }

        Intent intent = new Intent(getApplicationContext(), MiraclePhoneActivity.class);
        intent.putExtra("phoneNumberDao",phoneNumberItemCollectionDao);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
