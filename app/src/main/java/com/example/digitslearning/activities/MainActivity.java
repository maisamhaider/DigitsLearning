package com.example.digitslearning.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digitslearning.R;
import com.example.digitslearning.fragments.HomeFragment;
import com.example.digitslearning.fragments.ReportFragment;
import com.example.digitslearning.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView main_bnv = findViewById(R.id.main_bnv);
        loadFrag(HomeFragment.newInstance());
        main_bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_item:
                        loadFrag(HomeFragment.newInstance());
                        break;
                    case R.id.report_item:
                        loadFrag(ReportFragment.newInstance());
                        break;
                    case R.id.settings_item:
                        loadFrag(SettingsFragment.newInstance());
                        break;
                }
                return true;
            }
        });
    }
    public  void  loadFrag(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=  fm.beginTransaction();
        ft.replace(R.id.mainFragContainer,fragment).commit();
    }
}