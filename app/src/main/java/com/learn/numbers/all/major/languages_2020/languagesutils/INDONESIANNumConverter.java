package com.learn.numbers.all.major.languages_2020.languagesutils;

import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.Language;

import java.text.DecimalFormat;

public class INDONESIANNumConverter implements Language {

    public String[] sNum = new String[]{"nol","seratus","ribu","Juta","milyar","triliun"};
    public final String[] tensNames = {
            "", " sepuluh", " dua puluh", " tiga puluh", " empat puluh", " lima puluh",
            " enam puluh", " tujuh puluh", " delapan puluh", " sembilan puluh"
    };



    public final String[] numNames = {
            "",
            " satu", " dua", " tiga", " empat", " lima", " enam", " tujuh", " delapan", " sembilan",
            " sepuluh", " sebelas", " dua belas", " tiga belas", " empat belas", " lima belas",
            " enam belas", " tujuh belas", " delapan belas", " sembilan belas"
    };
    public INDONESIANNumConverter() {}

    public String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " seratus" + soFar;
    }


    public  String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "nol"; }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        if (billions == 0) {
            tradBillions = "";
        } else {
            tradBillions = convertLessThanOneThousand(billions) + " milyar ";
        }
        String result =  tradBillions;

        String tradMillions;
        if (millions == 0) {
            tradMillions = "";
        } else {
            tradMillions = convertLessThanOneThousand(millions) + " Juta ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "satu ribu ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " ribu ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
    @Override
    public String sNumber(String which) {

        switch (which) {
            case MAnnotation.ZERO:
                return sNum[0];
            case MAnnotation.HUNDRED:
                return sNum[1];
            case MAnnotation.THOUSAND:
                return sNum[2];
            case MAnnotation.MILLION:
                return sNum[3];
            case MAnnotation.BILLION:
                return sNum[4];
            case MAnnotation.TRILLION:
                return sNum[5];
        }
        return "";

    }
}
