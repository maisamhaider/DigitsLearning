package com.example.digitslearning.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.digitslearning.R;
import com.example.digitslearning.annotations.MAnnotation;


public class NumbersActivity extends BaseActivity implements View.OnClickListener {

    private String WHICH_NUMBERS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        initActiveConverter();
        WHICH_NUMBERS = getIntent().getStringExtra(MAnnotation.WHICH_NUMBERS);

        TextView targetWordNum_tv1 = findViewById(R.id.targetWordNum_tv1);
        TextView targetWordNum_tv2 = findViewById(R.id.targetWordNum_tv2);
        TextView targetWordNum_tv3 = findViewById(R.id.targetWordNum_tv3);
        TextView targetWordNum_tv4 = findViewById(R.id.targetWordNum_tv4);
        TextView targetWordNum_tv5 = findViewById(R.id.targetWordNum_tv5);
        TextView targetWordNum_tv6 = findViewById(R.id.targetWordNum_tv6);
        TextView targetWordNum_tv7 = findViewById(R.id.targetWordNum_tv7);
        TextView targetWordNum_tv8 = findViewById(R.id.targetWordNum_tv8);
        TextView targetWordNum_tv9 = findViewById(R.id.targetWordNum_tv9);

        ConstraintLayout cl1 = findViewById(R.id.cl1);
        ConstraintLayout cl2 = findViewById(R.id.cl2);
        ConstraintLayout cl3 = findViewById(R.id.cl3);
        ConstraintLayout cl4 = findViewById(R.id.cl4);
        ConstraintLayout cl5 = findViewById(R.id.cl5);
        ConstraintLayout cl6 = findViewById(R.id.cl6);
        ConstraintLayout cl7 = findViewById(R.id.cl7);
        ConstraintLayout cl8 = findViewById(R.id.cl8);
        ConstraintLayout cl9 = findViewById(R.id.cl9);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        TextView tv6 = findViewById(R.id.tv6);
        TextView tv7 = findViewById(R.id.tv7);
        TextView tv8 = findViewById(R.id.tv8);
        TextView tv9 = findViewById(R.id.tv9);


        if (WHICH_NUMBERS.matches(MAnnotation.ONE_TO_9))
        {
            OneTo9Fun(targetWordNum_tv1);
            OneTo9Fun(targetWordNum_tv2);
            OneTo9Fun(targetWordNum_tv3);
            OneTo9Fun(targetWordNum_tv4);
            OneTo9Fun(targetWordNum_tv5);
            OneTo9Fun(targetWordNum_tv9);
            OneTo9Fun(targetWordNum_tv6);
            OneTo9Fun(targetWordNum_tv7);
            OneTo9Fun(targetWordNum_tv8);

        }
        else if (WHICH_NUMBERS.matches(MAnnotation.ELEVEN_TO_19))
        {
            ElevenTo19Fun(targetWordNum_tv1,tv1);
            ElevenTo19Fun(targetWordNum_tv2,tv2);
            ElevenTo19Fun(targetWordNum_tv3,tv3);
            ElevenTo19Fun(targetWordNum_tv4,tv4);
            ElevenTo19Fun(targetWordNum_tv5,tv5);
            ElevenTo19Fun(targetWordNum_tv9,tv6);
            ElevenTo19Fun(targetWordNum_tv6,tv7);
            ElevenTo19Fun(targetWordNum_tv7,tv8);
            ElevenTo19Fun(targetWordNum_tv8,tv9);

        } else if (WHICH_NUMBERS.matches(MAnnotation.TEN_TO_90))
        {
            TenTo90(targetWordNum_tv1,tv1);
            TenTo90(targetWordNum_tv2,tv2);
            TenTo90(targetWordNum_tv3,tv3);
            TenTo90(targetWordNum_tv4,tv4);
            TenTo90(targetWordNum_tv5,tv5);
            TenTo90(targetWordNum_tv9,tv6);
            TenTo90(targetWordNum_tv6,tv7);
            TenTo90(targetWordNum_tv7,tv8);
            TenTo90(targetWordNum_tv8,tv9);
        }

        cl1.setOnClickListener(this);
        cl2.setOnClickListener(this);
        cl3.setOnClickListener(this);
        cl4.setOnClickListener(this);
        cl5.setOnClickListener(this);
        cl6.setOnClickListener(this);
        cl7.setOnClickListener(this);
        cl8.setOnClickListener(this);
        cl9.setOnClickListener(this);
    }

    public void OneTo9Fun(TextView view)
    {
        switch (view.getId()) {
            case R.id.targetWordNum_tv1:
                view.setText(converterObj.convert(1));
                break;
            case R.id.targetWordNum_tv2:
                view.setText(converterObj.convert(2));
                break;
            case R.id.targetWordNum_tv3:
                view.setText(converterObj.convert(3));
                break;
            case R.id.targetWordNum_tv4:
                view.setText(converterObj.convert(4));
                break;
            case R.id.targetWordNum_tv5:
                view.setText(converterObj.convert(5));
                break;
            case R.id.targetWordNum_tv6:
                view.setText(converterObj.convert(6));
                break;
            case R.id.targetWordNum_tv7:
                view.setText(converterObj.convert(7));
                break;
            case R.id.targetWordNum_tv8:
                view.setText(converterObj.convert(8));
                break;
            case R.id.targetWordNum_tv9:
                view.setText(converterObj.convert(9));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void ElevenTo19Fun(TextView view, TextView textView) {
        switch (view.getId()) {
            case R.id.targetWordNum_tv1:
                view.setText(converterObj.convert(11));
                textView.setText("11");
                break;
            case R.id.targetWordNum_tv2:
                view.setText(converterObj.convert(12));
                textView.setText("12");
                break;
            case R.id.targetWordNum_tv3:
                view.setText(converterObj.convert(13));
                textView.setText("13");
                break;
            case R.id.targetWordNum_tv4:
                view.setText(converterObj.convert(14));
                textView.setText("14");
                break;
            case R.id.targetWordNum_tv5:
                view.setText(converterObj.convert(15));
                textView.setText("15");
                break;
            case R.id.targetWordNum_tv6:
                view.setText(converterObj.convert(16));
                textView.setText("16");
                break;
            case R.id.targetWordNum_tv7:
                view.setText(converterObj.convert(17));
                textView.setText("17");
                break;
            case R.id.targetWordNum_tv8:
                view.setText(converterObj.convert(18));
                textView.setText("18");
                break;
            case R.id.targetWordNum_tv9:
                view.setText(converterObj.convert(19));
                textView.setText("19");
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    public void TenTo90(TextView viewNumWords, TextView viewNum)
    {
        switch (viewNumWords.getId()) {
            case R.id.targetWordNum_tv1:
                viewNumWords.setText(converterObj.convert(10));
                viewNum.setText("10");
                break;
            case R.id.targetWordNum_tv2:
                viewNumWords.setText(converterObj.convert(20));
                viewNum.setText("20");
                break;
            case R.id.targetWordNum_tv3:
                viewNumWords.setText(converterObj.convert(30));
                viewNum.setText("30");
                break;
            case R.id.targetWordNum_tv4:
                viewNumWords.setText(converterObj.convert(40));
                viewNum.setText("40");
                break;
            case R.id.targetWordNum_tv5:
                viewNumWords.setText(converterObj.convert(50));
                viewNum.setText("50");
                break;
            case R.id.targetWordNum_tv6:
                viewNumWords.setText(converterObj.convert(60));
                viewNum.setText("60");
                break;
            case R.id.targetWordNum_tv7:
                viewNumWords.setText(converterObj.convert(70));
                viewNum.setText("70");
                break;
            case R.id.targetWordNum_tv8:
                viewNumWords.setText(converterObj.convert(80));
                viewNum.setText("80");
                break;
            case R.id.targetWordNum_tv9:
                viewNumWords.setText(converterObj.convert(90));
                viewNum.setText("90");
                break;

        }
    }



    @Override
    public void onClick(View view) {
        if (WHICH_NUMBERS.matches(MAnnotation.ONE_TO_9)) {
            switch (view.getId()) {
                case R.id.cl1:
                    speakNum(converterObj.convert(1));
                    break;
                case R.id.cl2:
                    speakNum(converterObj.convert(2));
                    break;
                case R.id.cl3:
                    speakNum(converterObj.convert(3));
                    break;
                case R.id.cl4:
                    speakNum(converterObj.convert(4));
                    break;
                case R.id.cl5:
                    speakNum(converterObj.convert(5));
                    break;
                case R.id.cl6:
                    speakNum(converterObj.convert(6));
                    break;
                case R.id.cl7:
                    speakNum(converterObj.convert(7));
                    break;
                case R.id.cl8:
                    speakNum(converterObj.convert(8));
                    break;
                case R.id.cl9:
                    speakNum(converterObj.convert(9));
                    break;
            }

        } else if (WHICH_NUMBERS.matches(MAnnotation.ELEVEN_TO_19)){
            switch (view.getId()) {
                case R.id.cl1:
                    speakNum(converterObj.convert(11));
                    break;
                case R.id.cl2:
                    speakNum(converterObj.convert(12));
                    break;
                case R.id.cl3:
                    speakNum(converterObj.convert(13));
                    break;
                case R.id.cl4:
                    speakNum(converterObj.convert(14));
                    break;
                case R.id.cl5:
                    speakNum(converterObj.convert(15));
                    break;
                case R.id.cl6:
                    speakNum(converterObj.convert(16));
                    break;
                case R.id.cl7:
                    speakNum(converterObj.convert(17));
                    break;
                case R.id.cl8:
                    speakNum(converterObj.convert(18));
                    break;
                case R.id.cl9:
                    speakNum(converterObj.convert(19));
                    break;
            }
        }
        else if (WHICH_NUMBERS.matches(MAnnotation.TEN_TO_90))
        {
            switch (view.getId()) {
                case R.id.cl1:
                    speakNum(converterObj.convert(10));
                    break;
                case R.id.cl2:
                    speakNum(converterObj.convert(20));
                    break;
                case R.id.cl3:
                    speakNum(converterObj.convert(30));
                    break;
                case R.id.cl4:
                    speakNum(converterObj.convert(40));
                    break;
                case R.id.cl5:
                    speakNum(converterObj.convert(50));
                    break;
                case R.id.cl6:
                    speakNum(converterObj.convert(60));
                    break;
                case R.id.cl7:
                    speakNum(converterObj.convert(70));
                    break;
                case R.id.cl8:
                    speakNum(converterObj.convert(80));
                    break;
                case R.id.cl9:
                    speakNum(converterObj.convert(90));
                    break;
            }
        }
    }
}