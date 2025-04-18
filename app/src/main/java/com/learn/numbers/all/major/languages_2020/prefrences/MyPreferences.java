package com.learn.numbers.all.major.languages_2020.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;

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
    public void setData(boolean data, String key) {
        editor.putBoolean(key, data).commit();
    }
 public String getStringData(String key) {
     return sharedPreferences.getString(key, MAnnotation.ENGLISH);
 }
 public boolean getBooleanData(String key) {
     return sharedPreferences.getBoolean(key, false);
 }

}
