package com.learn.numbers.all.major.languages_2020.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.fragments.ExtraFragment;
import com.learn.numbers.all.major.languages_2020.fragments.HomeFragment;
import com.learn.numbers.all.major.languages_2020.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BottomNavigationView main_bnv = findViewById(R.id.main_bnv);
        loadFrag(HomeFragment.newInstance());
        main_bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_item:
                        loadFrag(HomeFragment.newInstance());
//                        sNewFragmentAds(HomeFragment.newInstance());
                        break;
                    case R.id.report_item:
                        loadFrag(ExtraFragment.newInstance());
                        break;
                    case R.id.settings_item:
//                        loadFrag()
                        sNewFragmentAds(SettingsFragment.newInstance()
                        );
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            exitt();
        }

    }

    public void exitt() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("InflateParams") final View dialogView =
                    layoutInflater.inflate(R.layout.exit_rateus, null);
            ConstraintLayout yes_cl = dialogView.findViewById(R.id.yes_cl);
            ConstraintLayout no_cl = dialogView.findViewById(R.id.no_cl);
            ConstraintLayout rateUs_cl = dialogView.findViewById(R.id.rateUs_cl);


            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            yes_cl.setOnClickListener(view -> {
                moveTaskToBack(true);
                alertDialog.cancel();

                finishAffinity();
            });

            no_cl.setOnClickListener(view ->
            {
//
                alertDialog.dismiss();

            });
            rateUs_cl.setOnClickListener(view ->
                    {
                        rate();
                    }
            );

        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void rate() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }

}