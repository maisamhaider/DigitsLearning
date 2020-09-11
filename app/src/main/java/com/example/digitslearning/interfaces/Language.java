package com.example.digitslearning.interfaces;

public interface Language {

    String[] tensNames = null;
    String[] sNum = null;
    String[] numNames = null;

    String convertLessThanOneThousand(int number);

    String convert(long number);
    String sNumber(String which);


}
