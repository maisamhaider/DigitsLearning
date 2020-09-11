package com.example.digitslearning.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.digitslearning.R;
import com.example.digitslearning.activities.CustomNumbersActivity;
import com.example.digitslearning.activities.NumbersActivity;
import com.example.digitslearning.activities.SpecialNumbersActivity;
import com.example.digitslearning.interfaces.TextChanged;
import com.example.digitslearning.prefrences.MyPreferences;

import static com.example.digitslearning.annotations.MAnnotation.ELEVEN_TO_19;
import static com.example.digitslearning.annotations.MAnnotation.ONE_TO_9;
import static com.example.digitslearning.annotations.MAnnotation.TEN_TO_90;
import static com.example.digitslearning.annotations.MAnnotation.WHICH_NUMBERS;


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
                Intent intent = new Intent(getContext(), NumbersActivity.class);
                intent.putExtra(WHICH_NUMBERS, ONE_TO_9);
                getContext().startActivity(intent);
            }
        });
        eleven_to_nineteen_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NumbersActivity.class);
                intent.putExtra(WHICH_NUMBERS, ELEVEN_TO_19);
                getContext().startActivity(intent);
            }
        });
        ten_to_ninety_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NumbersActivity.class);
                intent.putExtra(WHICH_NUMBERS, TEN_TO_90);
                getContext().startActivity(intent);
            }
        });
        specialNumbers_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SpecialNumbersActivity.class);
                getContext().startActivity(intent);
            }
        });
        customInput_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CustomNumbersActivity.class);
                getContext().startActivity(intent);
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