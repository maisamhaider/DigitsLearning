package com.example.digitslearning.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.digitslearning.R;

public class SettingsFragment extends Fragment {




    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {

        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View  view =  inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
}