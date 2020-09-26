package com.learn.numbers.all.major.languages_2020.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.prefrences.MyPreferences;

public class SpecialNumbersActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_numbers);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initActiveConverter();
        ConstraintLayout cl0 = findViewById(R.id.cl0);
        ConstraintLayout cl100 = findViewById(R.id.cl100);
        ConstraintLayout cl1000 = findViewById(R.id.cl1000);
        ConstraintLayout million_cl = findViewById(R.id.million_cl);
        ConstraintLayout billion_cl = findViewById(R.id.billion_cl);
        ConstraintLayout trillion_cl = findViewById(R.id.trillion_cl);

        TextView targetWordNum_tv0 = findViewById(R.id.targetWordNum_tv0);
        TextView targetWordNum_tv100 = findViewById(R.id.targetWordNum_tv100);
        TextView targetWordNum_tv1000 = findViewById(R.id.targetWordNum_tv1000);
        TextView millionTarget_tv = findViewById(R.id.millionTarget_tv);
        TextView billionTarget_tv = findViewById(R.id.billionTarget_tv);
        TextView trillionTarget_tv = findViewById(R.id.trillionTarget_tv);
        TextView sNumSLang_tv = findViewById(R.id.sNumSLang_tv);
        String sLang = new MyPreferences(this).getStringData(MAnnotation.S_LANGUAGE_KEY);
        sNumSLang_tv.setText(sLang);
        setNumWordInTvFun(targetWordNum_tv0);
        setNumWordInTvFun(targetWordNum_tv100);
        setNumWordInTvFun(targetWordNum_tv1000);
        setNumWordInTvFun(millionTarget_tv);
        setNumWordInTvFun(billionTarget_tv);
        setNumWordInTvFun(trillionTarget_tv);

        cl0.setOnClickListener(this);
        cl100.setOnClickListener(this);
        cl1000.setOnClickListener(this);
        million_cl.setOnClickListener(this);
        billion_cl.setOnClickListener(this);
        trillion_cl.setOnClickListener(this);
     }

    public void setNumWordInTvFun(TextView view)
    {
        switch (view.getId()) {
            case R.id.targetWordNum_tv0:
                view.setText(converterObj.sNumber(MAnnotation.ZERO));
                break;
            case R.id.targetWordNum_tv100:
                view.setText(converterObj.sNumber(MAnnotation.HUNDRED));
                break;
            case R.id.targetWordNum_tv1000:
                view.setText(converterObj.sNumber(MAnnotation.THOUSAND));
                break;
            case R.id.millionTarget_tv:
                view.setText(converterObj.sNumber(MAnnotation.MILLION));
                break;
            case R.id.billionTarget_tv:
                view.setText(converterObj.sNumber(MAnnotation.BILLION));
                break;
            case R.id.trillionTarget_tv:
                view.setText(converterObj.sNumber(MAnnotation.TRILLION));
                break;

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cl0:
                adOnNumClick(converterObj.sNumber(MAnnotation.ZERO));
                break;
            case R.id.cl100:
                speakNum(converterObj.sNumber(MAnnotation.HUNDRED));
                break;
            case R.id.cl1000:
                adOnNumClick(converterObj.sNumber(MAnnotation.THOUSAND));
                break;
            case R.id.million_cl:
                adOnNumClick(converterObj.sNumber(MAnnotation.MILLION));
                break;
            case R.id.billion_cl:
                speakNum(converterObj.sNumber(MAnnotation.BILLION));
                break;
            case R.id.trillion_cl:
                adOnNumClick(converterObj.sNumber(MAnnotation.TRILLION));
                break;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        shutDown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shutDown();
    }


}