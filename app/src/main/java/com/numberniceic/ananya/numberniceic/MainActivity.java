package com.numberniceic.ananya.numberniceic;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.numberniceic.ananya.numberniceic.activities.MiraclePhoneActivity;
import com.numberniceic.ananya.numberniceic.dao.phone.PhoneNumberItemCollectionDao;
import com.numberniceic.ananya.numberniceic.fragments.name.BornFragment;
import com.numberniceic.ananya.numberniceic.fragments.name.NameFragment;
import com.numberniceic.ananya.numberniceic.fragments.tabian.ProvinceFragment;
import com.numberniceic.ananya.numberniceic.fragments.tabian.TabianFragment;
import com.numberniceic.ananya.numberniceic.fragments.telephone.TelephoneFragment;

public class MainActivity extends AppCompatActivity implements TelephoneFragment.FragmentTelePhoneListener, ProvinceFragment.OnProvinceClickListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    String message;

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



    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();


    }

    @Override
    public void onProvinceClick(String province) {
        this.message = province;

    }

}
