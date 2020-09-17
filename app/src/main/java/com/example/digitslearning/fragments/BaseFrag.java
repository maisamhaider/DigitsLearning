package com.example.digitslearning.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitslearning.R;
import com.example.digitslearning.adapters.LanguagesAdapter;
import com.example.digitslearning.annotations.MAnnotation;
import com.example.digitslearning.interfaces.TextChanged;
import com.example.digitslearning.prefrences.MyPreferences;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class BaseFrag extends Fragment {
    private MyPreferences myPreferences;
    private InterstitialAd interstitialAd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new MyPreferences(getActivity());
        loadInterstitial();
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }});
    }

    public void loadInterstitial() {
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.interstitial));
        try {
            if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqNewInterstitial();

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void reqNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }

    public void newActivityAds(final Activity activity, final String key, final String data) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(getActivity(), activity.getClass());
            intent.putExtra(key, data);
            startActivity(intent);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                Intent intent = new Intent(getActivity(), activity.getClass());
                intent.putExtra(key, data);
                startActivity(intent);
            }
        });

    }
    public void newActivityAds(final Activity activity) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(getActivity(), activity.getClass());
            startActivity(intent);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                Intent intent = new Intent(getActivity(), activity.getClass());
                startActivity(intent);
            }
        });

    }
    public void adView(final AdView adView) {

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded() {
                adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError var1) {
                adView.setVisibility(View.GONE);
            }

        });
    }

    @SuppressLint("SetTextI18n")
    public void sNameOfLang(TextView textView) {
        switch (myPreferences.getStringData(MAnnotation.S_LANGUAGE_KEY)) {
            case MAnnotation.CHINESE:
                textView.setText(MAnnotation.CHINESE+"  ");
                break;
            case MAnnotation.ENGLISH:
                textView.setText(MAnnotation.ENGLISH+"  ");
                break;
            case MAnnotation.FRENCH:
                textView.setText(MAnnotation.FRENCH+"  ");
                break;
            case MAnnotation.GERMAN:
                textView.setText(MAnnotation.GERMAN+"  ");
                break;
            case MAnnotation.GREEK:
                textView.setText(MAnnotation.GREEK+"  ");
                break;
            case MAnnotation.HEBREW:
                textView.setText(MAnnotation.HEBREW+"  ");
                break;
            case MAnnotation.ITALIAN:
                textView.setText(MAnnotation.ITALIAN+"  ");
                break;
            case MAnnotation.JAPANESE:
                textView.setText(MAnnotation.JAPANESE+"  ");
                break;
            case MAnnotation.KOREAN:
                textView.setText(MAnnotation.KOREAN+"  ");
                break;
            case MAnnotation.LATIN:
                textView.setText(MAnnotation.LATIN+"  ");
                break;
            case MAnnotation.SPANISH:
                textView.setText(MAnnotation.SPANISH+"  ");
                break;
            default:
                textView.setText(MAnnotation.ENGLISH+"  ");
        }

    }

    public void showLanguagesDialog(Context context, TextChanged textChanged) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_language_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.show();
        ArrayList<String> languages = new ArrayList<>();
        languages.add(MAnnotation.CHINESE);
        languages.add(MAnnotation.ENGLISH);
        languages.add(MAnnotation.FRENCH);
        languages.add(MAnnotation.GERMAN);
        languages.add(MAnnotation.GREEK);
        languages.add(MAnnotation.HEBREW);
        languages.add(MAnnotation.ITALIAN);
        languages.add(MAnnotation.JAPANESE);
        languages.add(MAnnotation.KOREAN);
        languages.add(MAnnotation.LATIN);
        languages.add(MAnnotation.SPANISH);
        TextView done_tv = view.findViewById(R.id.done_tv);
        RecyclerView languages_rv = view.findViewById(R.id.languages_rv);
        LanguagesAdapter adapter = new LanguagesAdapter(context, languages);
        adapter.init(textChanged);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        languages_rv.setLayoutManager(layoutManager);
        languages_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }



}
