package com.learn.numbers.all.major.languages_2020.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.numbers.all.major.languages_2020.R;

import java.util.ArrayList;
import java.util.Locale;

public class HundredsNumbsAdapter extends RecyclerView.Adapter<HundredsNumbsAdapter.NumbsHolder> {

    private Context context;
    private ArrayList<String> numberArray;
    private String[] numberWordArray;
    private String[] numPronunciationArray;

    TextToSpeech textToSpeech;
    private int counter=0;

    public HundredsNumbsAdapter(Context context, ArrayList<String> numberArray,
                                String[] numberWordArray, String[] numPronunciationArray) {
        this.context = context;
        this.numberArray = numberArray;
        this.numberWordArray = numberWordArray;
        this.numPronunciationArray = numPronunciationArray;
    }

    @NonNull
    @Override
    public NumbsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hundred_numbs_item,
                parent, false);
        return new NumbsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NumbsHolder holder, int position) {

        String langNum = numberArray.get(position);
        String mathNum = String.valueOf(position);
        String numWord = numberWordArray[position];
        String numPronounce = numPronunciationArray[position];

        holder.num_tv1.setText(langNum);
        holder.num_tv2.setText(mathNum);
        holder.numWord_tv.setText(numWord);
        holder.numPronounce_tv.setText(numPronounce);


        holder.hNum_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.US);
                        if (textToSpeech.isSpeaking() && counter!=1){
                                return;
                        }
                        else{
                            counter = 0;
                        }
                        counter++;
                        textToSpeech.setSpeechRate(1f);
                        textToSpeech.speak(holder.numPronounce_tv.getText(),
                                TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });
            }
        });


    }

    public void shutDown() {

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public int getItemCount() {
        return 101;
    }

    class NumbsHolder extends RecyclerView.ViewHolder {
        TextView num_tv1, num_tv2, numWord_tv, numPronounce_tv;
        ConstraintLayout hNum_cl;

        public NumbsHolder(@NonNull View itemView) {
            super(itemView);
            num_tv1 = itemView.findViewById(R.id.num_tv1);
            num_tv2 = itemView.findViewById(R.id.num_tv2);
            numWord_tv = itemView.findViewById(R.id.numWord_tv);
            numPronounce_tv = itemView.findViewById(R.id.numPronounce_tv);
            hNum_cl = itemView.findViewById(R.id.hNum_cl);
        }
    }

}
