package com.example.digitslearning.utils;

import java.text.DecimalFormat;

public class CHINESENumConverter {

    public final String[] tensNames = {
            "",
            " yi-shi",
            " er-shi",
            " san-shi",
            " si-shi",
            " wu-shi",
            " liu-shi",
            " qi-shi",
            " ba-shi",
            " jiu-shi",
     };

    public final String[] numNames = {
            "",
            " yi",
            " er",
            " san",
            " si",
            " wu",
            " liu",
            " qi",
            " ba",
            " jiu",
            " shi",
            " shi-yi",
            " shi-er",
            " shi-san",
            " shi-si",
            " shi-wu",
            " shi-liu",
            " shi-qi",
            " shi-ba",
            " shi-jiu"
    };
    public CHINESENumConverter() {}

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
        return numNames[number] + "-bai" + soFar;
    }


    public  String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "líng"; }

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
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(billions) + "-yì ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions) + "-yì ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions) + " Bǎi wàn ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions) + " Bǎi wàn ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "yi-qian ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + "-qian ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
}
