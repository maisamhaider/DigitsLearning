package com.example.digitslearning.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.digitslearning.annotations.MAnnotation;

public class MyPreferences {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MyPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setData(String data, String key) {
        editor.putString(key, data).commit();
    }
 public String getStringData(String key) {
     return sharedPreferences.getString(key, MAnnotation.ENGLISH);
 }

}
