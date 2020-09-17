package com.example.digitslearning.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digitslearning.R;
import com.example.digitslearning.prefrences.MyPreferences;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends AppCompatActivity {
     InterstitialAd interstitialAd;
    private CheckBox accept_box;
    Button close_btn;
    Button ok_btn;
    View splash_v, terms_v;
    boolean previouslyStarted;
    MyPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        reqNewInterstitial();
        close_btn = findViewById(R.id.close_btn);
        ok_btn = findViewById(R.id.ok_btn);
        splash_v = findViewById(R.id.splash_v);
        terms_v = findViewById(R.id.terms_v);
        preferences = new MyPreferences(this);


        previouslyStarted = preferences.getBooleanData("if_Accept");

         LWithAds(this);

    }

    public void loadFun() {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (previouslyStarted) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    ok_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferences.setData(true, "if_Accept");
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    });

                    close_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    splash_v.setVisibility(View.GONE);
                    terms_v.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);
    }

    public void LWithAds(final Context context) {
        try {
            ProgressDialog showDialog = ProgressDialog.show(context, getString(R.string.app_name), "Please wait for few seconds", true);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                showDialog.dismiss();
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    loadFun();
                }
                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        reqNewInterstitial();
                        loadFun();
                    }
                });
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqNewInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }
}