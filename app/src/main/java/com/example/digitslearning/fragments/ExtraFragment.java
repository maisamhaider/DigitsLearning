package com.example.digitslearning.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digitslearning.R;
import com.example.digitslearning.activities.ExtraLanguagesNumActivity;
import com.example.digitslearning.annotations.MAnnotation;
import com.google.android.gms.ads.AdView;


public class ExtraFragment extends BaseFrag {


    public ExtraFragment() {
        // Required empty public constructor
    }

    public static ExtraFragment newInstance() {

        return new ExtraFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extra, container, false);
        AdView aView = view.findViewById(R.id.extraFrag_adView);
        adView(aView);
         view.findViewById(R.id.one_to_100_arabic_cl).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new ExtraLanguagesNumActivity(),MAnnotation.S_Extra_LANGUAGE_KEY,
                        MAnnotation.ARABIC);
            }
        }); view.findViewById(R.id.one_to_100_persian_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new ExtraLanguagesNumActivity(),MAnnotation.S_Extra_LANGUAGE_KEY,
                        MAnnotation.PERSIAN);
            }
        }); view.findViewById(R.id.one_to_100_pakhtu_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new ExtraLanguagesNumActivity(),MAnnotation.S_Extra_LANGUAGE_KEY,
                        MAnnotation.PAKHTU);
            }
        }); view.findViewById(R.id.one_to_100_urdu_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new ExtraLanguagesNumActivity(),MAnnotation.S_Extra_LANGUAGE_KEY,
                        MAnnotation.URDU);}});
        return view;

    }
}