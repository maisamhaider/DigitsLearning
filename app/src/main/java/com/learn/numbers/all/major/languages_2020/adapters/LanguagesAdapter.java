package com.learn.numbers.all.major.languages_2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.learn.numbers.all.major.languages_2020.R;
import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.TextChanged;
import com.learn.numbers.all.major.languages_2020.prefrences.MyPreferences;

import java.util.ArrayList;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguageHolder> {

    private Context context;
    private ArrayList<String> languagesAL;
    private ArrayList<String> list = new ArrayList<>();
    private MyPreferences preferences;
    private TextChanged textChanged;
    private int selected_position = -1;
    InterstitialAd interstitialAd;

    public LanguagesAdapter(Context context, ArrayList<String> languagesAL) {
        this.context = context;
        this.languagesAL = languagesAL;
        preferences = new MyPreferences(context);
        this.list.add(preferences.getStringData(MAnnotation.S_LANGUAGE_KEY));
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial));
        reqNewInterstitial();
    }

    @NonNull
    @Override
    public LanguageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_name_item, parent, false);
        return new LanguageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageHolder holder, final int position) {
        final String cLanguageName = languagesAL.get(position);

        holder.languageName_tv.setText(cLanguageName);

        imageVisibility(holder.sLanguageItem_iv, cLanguageName);

        holder.languageItemMain_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (interstitialAd != null && interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    }
                    else
                        {
                            final String name = languagesAL.get(position);
                            String lastLang = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
                            list.remove(lastLang);
                            preferences.setData(name, MAnnotation.S_LANGUAGE_KEY);
                            String newLang = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
                            list.add(newLang);
//                imageVisibility(holder.sLanguageItem_iv, newLang);
                            if (selected_position == position) {
                                holder.sLanguageItem_iv.setVisibility(View.VISIBLE);
                                selected_position = -1;
                                notifyDataSetChanged();
                                return;
                            }
                            selected_position = position;
                            textChanged.changedText(newLang);
                            notifyDataSetChanged();
                    }
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            reqNewInterstitial();
                            final String name = languagesAL.get(position);
                            String lastLang = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
                            list.remove(lastLang);
                            preferences.setData(name, MAnnotation.S_LANGUAGE_KEY);
                            String newLang = preferences.getStringData(MAnnotation.S_LANGUAGE_KEY);
                            list.add(newLang);
//                imageVisibility(holder.sLanguageItem_iv, newLang);
                            if (selected_position == position) {
                                holder.sLanguageItem_iv.setVisibility(View.VISIBLE);
                                selected_position = -1;
                                notifyDataSetChanged();
                                return;
                            }
                            selected_position = position;
                            textChanged.changedText(newLang);
                            notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void imageVisibility(ImageView v, String s) {

        if (list.contains(s)) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return languagesAL.size();
    }

    class LanguageHolder extends RecyclerView.ViewHolder {
        TextView languageName_tv;
        ImageView sLanguageItem_iv;
        RelativeLayout languageItemMain_cl;

        public LanguageHolder(@NonNull View itemView) {
            super(itemView);
            languageName_tv = itemView.findViewById(R.id.languageName_tv);
            sLanguageItem_iv = itemView.findViewById(R.id.sLanguageItem_iv);
            languageItemMain_cl = itemView.findViewById(R.id.languageItemMain_cl);
        }
    }

    public void reqNewInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void init(TextChanged textChanged) {
        this.textChanged = textChanged;
    }
}
