package com.learn.numbers.all.major.languages_2020.languagesutils;

import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.Language;

import java.text.DecimalFormat;

public class JAPANESENumConverter  implements Language {
    public String[] sNum = new String[]{"zero","hyaku","sen","hyaku-man","Oku",
            "Chō"};
    public final String[] tensNames = {
            "",
            " iti-zyuu", " ni-zyuu", " sen-zyuu", " si-zyuu", " go-zyuu", " roku-zyuu",
            " siti-zyuu", " hati-zyuu", " kyuu-zyuu",
    };

    public final String[] numNames = {
            "", " iti", " ni", " sen", " si", " go", " roku", " siti", " hati", " kyuu", " zyuu",
            " zyuu-iti", " zyuu-ni", " zyuu-sen", " zyuu-si", " zyuu-go", " zyuu-roku",
            " zyuu-siti", " zyuu-hati", " zyuu-kyuu"
    };
    public JAPANESENumConverter() {}

    @Override
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
        return numNames[number] + "-hyaku" + soFar;
    }

    @Override
    public String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

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
                tradBillions = convertLessThanOneThousand(billions) + "-Oku ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions) + "-Oku ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions) + "-hyaku-man ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions) + "-hyaku-man ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "-iti-sen ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + "-sen ";
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





