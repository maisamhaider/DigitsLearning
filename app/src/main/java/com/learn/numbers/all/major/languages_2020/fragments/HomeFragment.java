package com.learn.numbers.all.major.languages_2020.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.activities.CustomNumbersActivity;
import com.learn.numbers.all.major.languages_2020.activities.NumbersActivity;
import com.learn.numbers.all.major.languages_2020.activities.SpecialNumbersActivity;
import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.TextChanged;
import com.learn.numbers.all.major.languages_2020.prefrences.MyPreferences;
import com.google.android.gms.ads.AdView;


public class HomeFragment extends BaseFrag {

    private MyPreferences preferences;
    TextView sSelected_language_tv;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        preferences = new MyPreferences(getContext());

        AdView aView = view.findViewById(R.id.homeFrag_adView);
        adView(aView);
        sSelected_language_tv = view.findViewById(R.id.sSelected_language_tv);
        ImageView home_lang_change_iv = view.findViewById(R.id.home_lang_change_iv);
        sNameOfLang(sSelected_language_tv);
        ConstraintLayout one_to_nine_cl = view.findViewById(R.id.one_to_nine_cl);
        ConstraintLayout eleven_to_nineteen_cl = view.findViewById(R.id.eleven_to_nineteen_cl);
        ConstraintLayout ten_to_ninety_cl = view.findViewById(R.id.ten_to_ninety_cl);
        ConstraintLayout specialNumbers_cl = view.findViewById(R.id.specialNumbers_cl);
        ConstraintLayout customInput_cl = view.findViewById(R.id.customInput_cl);

        one_to_nine_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersActivity(), MAnnotation.WHICH_NUMBERS,
                        MAnnotation.ONE_TO_9);
            }
        });
        eleven_to_nineteen_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersActivity(), MAnnotation.WHICH_NUMBERS,
                        MAnnotation.ELEVEN_TO_19);
            }
        });
        ten_to_ninety_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersActivity(), MAnnotation.WHICH_NUMBERS,
                        MAnnotation.TEN_TO_90);
            }
        });
        specialNumbers_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new SpecialNumbersActivity());
            }
        });
        customInput_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new CustomNumbersActivity());
            }
        });
        home_lang_change_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLanguagesDialog(getActivity(), new TextChanged() {
                    @Override
                    public boolean changedText(String val) {
                        sSelected_language_tv.setText(val);
                        return false;
                    }
                });
            }
        });

        return view;
    }


}