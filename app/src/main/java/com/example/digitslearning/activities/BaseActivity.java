package com.example.digitslearning.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digitslearning.R;
import com.example.digitslearning.annotations.MAnnotation;
import com.example.digitslearning.interfaces.Language;
import com.example.digitslearning.languagesutils.CHINESENumConverter;
import com.example.digitslearning.languagesutils.ENGLISHNumConverter;
import com.example.digitslearning.languagesutils.FRENCHNumConverter;
import com.example.digitslearning.languagesutils.GERMANNumConverter;
import com.example.digitslearning.languagesutils.GREEKumConverter;
import com.example.digitslearning.languagesutils.HEBREWNumConverter;
import com.example.digitslearning.languagesutils.ITALIANNumConverter;
import com.example.digitslearning.languagesutils.JAPANESENumConverter;
import com.example.digitslearning.languagesutils.KOREANNumConverter;
import com.example.digitslearning.languagesutils.LATINNumConverter;
import com.example.digitslearning.languagesutils.SPANISHNumConverter;
import com.example.digitslearning.prefrences.MyPreferences;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private MyPreferences myPreferences;
    public Language converterObj;
    private TextToSpeech textToSpeech;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new MyPreferences(BaseActivity.this);
        loadInterstitial();
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }});
        initActiveConverter();

    }

    public void loadInterstitial() {
        interstitialAd = new InterstitialAd(getApplicationContext());
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

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
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

    public void sNewActivityAds(final Activity activity,String key, final String data) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(getApplicationContext(), activity.getClass());
            intent.putExtra(key, data);
            startActivity(intent);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                Intent intent = new Intent(getApplicationContext(), activity.getClass());
                intent.putExtra("key", data);
                startActivity(intent);
            }
        });

    }
    public void sNewFragmentAds(final Fragment fragment) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            loadFrag(fragment);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                loadFrag(fragment);
            }
        });

    }
    public void adOnNumClick(final long number) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            speakNum(converterObj.convert(number));
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                speakNum(converterObj.convert(number));

            }
        });
    }
        public void adOnNumClick(final String s) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            speakNum( s );
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                speakNum(s);

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
    public  void  loadFrag(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=  fm.beginTransaction();
        ft.replace(R.id.mainFragContainer,fragment).commit();
    }
    public void initActiveConverter() {

        switch (myPreferences.getStringData(MAnnotation.S_LANGUAGE_KEY)) {
            case MAnnotation.CHINESE:
                converterObj = new CHINESENumConverter();
                break;
            case MAnnotation.ENGLISH:
                converterObj = new ENGLISHNumConverter();
                break;
            case MAnnotation.FRENCH:
                converterObj = new FRENCHNumConverter();
                break;
            case MAnnotation.GERMAN:
                converterObj = new GERMANNumConverter();
                break;
            case MAnnotation.GREEK:
                converterObj = new GREEKumConverter();
                break;
            case MAnnotation.HEBREW:
                converterObj = new HEBREWNumConverter();
                break;
            case MAnnotation.ITALIAN:
                converterObj = new ITALIANNumConverter();
                break;
            case MAnnotation.JAPANESE:
                converterObj = new JAPANESENumConverter();
                break;
            case MAnnotation.KOREAN:
                converterObj = new KOREANNumConverter();
                break;
            case MAnnotation.LATIN:
                converterObj = new LATINNumConverter();
                break;
            case MAnnotation.SPANISH:
                converterObj = new SPANISHNumConverter();
                break;
            default:
                converterObj = new ENGLISHNumConverter();

        }
    }

    public void speakNum(final String numString) {
        switch (myPreferences.getStringData(MAnnotation.S_LANGUAGE_KEY)) {
            case MAnnotation.CHINESE:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.CHINESE);
//                        Voice voice = new Voice("male",Locale.CHINESE,400,200,true,null);
//                         textToSpeech.setSpeechRate(0.5f);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }

                    }
                });
                break;
            case MAnnotation.FRENCH:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.FRANCE);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
                break;
            case MAnnotation.GERMAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.GERMAN);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
                break;
            case MAnnotation.ITALIAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.ITALIAN);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
                break;
            case MAnnotation.JAPANESE:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
                break;
            case MAnnotation.KOREAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.KOREAN);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
                break;
            default:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });

        }

    }

}
