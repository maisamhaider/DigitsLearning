package com.example.digitslearning.activities;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digitslearning.annotations.MAnnotation;
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
import com.example.digitslearning.interfaces.Language;
import com.example.digitslearning.languagesutils.SPANISHNumConverter;
import com.example.digitslearning.prefrences.MyPreferences;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    private MyPreferences myPreferences;
    public Language converterObj;
    private TextToSpeech textToSpeech ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new MyPreferences(BaseActivity.this);

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
    public void speakNum(final String numString)
    {
        switch (myPreferences.getStringData(MAnnotation.S_LANGUAGE_KEY)) {
            case MAnnotation.CHINESE:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.CHINESE);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }

                    }
                });
                 break;
            case MAnnotation.FRENCH:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.FRANCE);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });
                 break;
            case MAnnotation.GERMAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.GERMAN);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });
                 break;
            case MAnnotation.ITALIAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.ITALIAN);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });
                 break;
            case MAnnotation.JAPANESE:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });
                 break;
            case MAnnotation.KOREAN:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.KOREAN);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });
                 break;
            default:
                textToSpeech = new TextToSpeech(BaseActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        if(!textToSpeech.isSpeaking())
                        {
                            textToSpeech.speak(numString,TextToSpeech.QUEUE_FLUSH,null,null);
                        }                    }
                });

        }

    }
}
