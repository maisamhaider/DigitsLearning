package com.example.digitslearning.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.digitslearning.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculator_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculator_Fragment extends Fragment {


    public Calculator_Fragment() {
        // Required empty public constructor
    }

    public static Calculator_Fragment newInstance() {

        return new Calculator_Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        return view;
    }
}