package com.example.digitslearning.models;

import java.util.ArrayList;

public class HundredNumbersModel {
    private ArrayList<String> numberArray;
     private String[] numberWordArray;
    private String[] numPronunciationArray;

    public HundredNumbersModel(ArrayList<String> numberArray, String[] numberWordArray, String[] numPronunciationArray) {
        this.numberArray = numberArray;
        this.numberWordArray = numberWordArray;
        this.numPronunciationArray = numPronunciationArray;
    }

    public ArrayList<String> getNumberArray() {
        return numberArray;
    }

    public String[] getNumberWordArray() {
        return numberWordArray;
    }

    public String[] getNumPronunciationArray() {
        return numPronunciationArray;
    }
}
