package com.learn.numbers.all.major.languages_2020.activities;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.Language;
import com.learn.numbers.all.major.languages_2020.languagesutils.CHINESENumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.ENGLISHNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.FRENCHNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.GERMANNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.GREEKumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.HEBREWNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.INDONESIANNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.ITALIANNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.JAPANESENumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.KOREANNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.LATINNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.PORTUGUESENumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.RUSSIANNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.SPANISHNumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.THAINumConverter;
import com.learn.numbers.all.major.languages_2020.languagesutils.TURKIShNumConverter;
import com.learn.numbers.all.major.languages_2020.prefrences.MyPreferences;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private MyPreferences myPreferences;
    public Language converterObj;
    private TextToSpeech textToSpeech;
    InterstitialAd interstitialAd;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new MyPreferences(BaseActivity.this);
        loadInterstitial();
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        initActiveConverter();
        textToSpeech = new TextToSpeech(BaseActivity.this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                    }
                });

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

//    public void sNewActivityAds(final Activity activity, String key, final String data) {
//        if (interstitialAd != null && interstitialAd.isLoaded()) {
//            interstitialAd.show();
//        } else {
//            Intent intent = new Intent(getApplicationContext(), activity.getClass());
//            intent.putExtra(key, data);
//            startActivity(intent);
//        }
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                reqNewInterstitial();
//                Intent intent = new Intent(getApplicationContext(), activity.getClass());
//                intent.putExtra("key", data);
//                startActivity(intent);
//            }
//        });
//
//    }

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

//    public void adOnNumClick(final long number) {
//        if (interstitialAd != null && interstitialAd.isLoaded()) {
//            interstitialAd.show();
//        } else {
//
//            speakNum(converterObj.convert(number));
//        }
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                speakNum(converterObj.convert(number));
//                reqNewInterstitial();
//
//            }
//        });
//    }

    public void adOnNumClick(final String s) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            speakNum(s);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                speakNum(s);
                reqNewInterstitial();

            }
        });

    }

    public void adView(final AdView adView) {

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
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

    public void loadFrag(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainFragContainer, fragment).commit();
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
//            case MAnnotation.GEORGIAN:
//                converterObj = new GEORGIANNumConverter();
//                break;
            case MAnnotation.GERMAN:
                converterObj = new GERMANNumConverter();
                break;
            case MAnnotation.GREEK:
                converterObj = new GREEKumConverter();
                break;
            case MAnnotation.HEBREW:
                converterObj = new HEBREWNumConverter();
                break;
            case MAnnotation.INDONESIAN:
                converterObj = new INDONESIANNumConverter();
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
            case MAnnotation.PORTUGUESE:
                converterObj = new PORTUGUESENumConverter();
                break;
            case MAnnotation.RUSSIAN:
                converterObj = new RUSSIANNumConverter();
                break;
            case MAnnotation.SPANISH:
                converterObj = new SPANISHNumConverter();
                break;
            case MAnnotation.THAI:
                converterObj = new THAINumConverter();
                break;
            case MAnnotation.TURKISH:
                converterObj = new TURKIShNumConverter();
                break;
            default:
                converterObj = new ENGLISHNumConverter();

        }
    }

    public void speakNum(final String numString) {
        switch (myPreferences.getStringData(MAnnotation.S_LANGUAGE_KEY)) {

            case MAnnotation.FRENCH:
                textToSpeech = new TextToSpeech(BaseActivity.this,
                        new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                 textToSpeech.setLanguage(Locale.FRANCE);
                                if (textToSpeech.isSpeaking() && counter != 1) {
                                    return;
                                } else {
                                    counter = 0;
                                }

                                counter++;
                                textToSpeech.setSpeechRate(1f);
                                textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null,
                                        null);
                            }
                        });
                break;
            case MAnnotation.GERMAN:
                textToSpeech = new TextToSpeech(BaseActivity.this,
                        new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                textToSpeech.setLanguage(Locale.GERMAN);
                                if (textToSpeech.isSpeaking() && counter != 1) {
                                    return;
                                } else {
                                    counter = 0;
                                }

                                counter++;
                                textToSpeech.setSpeechRate(1f);
                                textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null,
                                        null);}
                });
                break;
            case MAnnotation.ITALIAN:
                textToSpeech = new TextToSpeech(BaseActivity.this,
                        new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                textToSpeech.setLanguage(Locale.ITALIAN);
                                if (textToSpeech.isSpeaking() && counter != 1) {
                                    return;
                                } else {
                                    counter = 0;
                                }
                                counter++;
                                textToSpeech.setSpeechRate(1f);
                                textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null,
                                        null);
                            }
                        });
                break;

            case MAnnotation.KOREAN:

                textToSpeech = new TextToSpeech(BaseActivity.this,
                        new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                textToSpeech.setLanguage(Locale.KOREAN);
                                if (textToSpeech.isSpeaking() && counter != 1) {
                                    return;
                                } else {
                                    counter = 0;
                                }
                                counter++;
                                textToSpeech.setSpeechRate(1f);
                                textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH, null,
                                        null); }});
                break;
            default:
                textToSpeech.setLanguage(Locale.US);
                if (textToSpeech.isSpeaking() && counter != 1) {
                    return; }
                else {counter = 0;}
                counter++;
                textToSpeech.setSpeechRate(1f);
                    textToSpeech.speak(numString, TextToSpeech.QUEUE_FLUSH,
                            null, null); }}
    public void shutDown() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
