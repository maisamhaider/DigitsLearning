package com.example.digitslearning.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digitslearning.R;
import com.example.digitslearning.annotations.MAnnotation;
import com.example.digitslearning.prefrences.MyPreferences;

public class CustomNumbersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_numbers);
        initActiveConverter();
        MyPreferences preferences = new MyPreferences(CustomNumbersActivity.this);
        final EditText inputNumber_mEt = findViewById(R.id.inputNumber_mEt);
        TextView customActSLang_mtv = findViewById(R.id.customActSLang_mtv);
        final TextView result_mTv = findViewById(R.id.result_mTv);
        final ImageView custom_num_speak_iv = findViewById(R.id.custom_num_speak_iv);
        Button convertNumber_mBtn = findViewById(R.id.convertNumber_mBtn);

        String sLanguage = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
        customActSLang_mtv.setText(sLanguage);

        convertNumber_mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long num = Long.parseLong(inputNumber_mEt.getText().toString());
                result_mTv.setText(converterObj.convert(num));
            }
        });
        custom_num_speak_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numWord = result_mTv.getText().toString();
                speakNum(numWord);
            }
        });
    }
}