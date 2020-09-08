package com.example.digitslearning.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.digitslearning.R;
import com.example.digitslearning.utils.CHINESENumConverter;


public class HomeFragment extends Fragment {

    CHINESENumConverter converter;
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
        converter = new CHINESENumConverter();
        final EditText input_et = view.findViewById(R.id.input_et);
        final TextView result_tv = view.findViewById(R.id.result_tv);
        Button result_btn = view.findViewById(R.id.result_btn);

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String result =  converter.convert(Long.parseLong(input_et.getText().toString()));
                result_tv.setText(result);

            }
        });
        return view;
    }
}