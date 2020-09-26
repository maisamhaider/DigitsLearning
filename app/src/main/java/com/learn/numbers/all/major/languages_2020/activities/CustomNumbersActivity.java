package com.learn.numbers.all.major.languages_2020.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.prefrences.MyPreferences;

public class CustomNumbersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_numbers);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initActiveConverter();
        MyPreferences preferences = new MyPreferences(CustomNumbersActivity.this);
        final EditText inputNumber_mEt = findViewById(R.id.inputNumber_mEt);
        TextView customActSLang_mtv = findViewById(R.id.customActSLang_mtv);
        final TextView result_mTv = findViewById(R.id.result_mTv);
        final ImageView custom_num_speak_iv = findViewById(R.id.custom_num_speak_iv);
        TextView convertNumber_tv = findViewById(R.id.convertNumber_tv);

        String sLanguage = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
        customActSLang_mtv.setText(sLanguage);

        convertNumber_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputNumber_mEt.getText().toString().matches("")) {
                    long num = Long.parseLong(inputNumber_mEt.getText().toString());
                    result_mTv.setText(converterObj.convert(num));
                    String numWord = result_mTv.getText().toString();
                    custom_num_speak_iv.setVisibility(View.VISIBLE);
                    speakNum(numWord);

                }
            }
        });
        custom_num_speak_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numWord = result_mTv.getText().toString();
                speakNum(numWord);
            }
        });
        inputNumber_mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!inputNumber_mEt.getText().toString().matches("")) {
                    long num = Long.parseLong(inputNumber_mEt.getText().toString());
                    result_mTv.setText(converterObj.convert(num));

                }
            }
        });

    }

    @Override
    protected void onPause() {
        shutDown();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        shutDown();
        super.onDestroy();
    }

}