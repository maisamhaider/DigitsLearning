package com.learn.numbers.all.major.languages_2020.languagesutils;

import com.learn.numbers.all.major.languages_2020.annotations.MAnnotation;
import com.learn.numbers.all.major.languages_2020.interfaces.Language;

import java.text.DecimalFormat;

public class RUSSIANNumConverter implements Language {

    public String[] sNum = new String[]{"nul","sotnya","tysyacha","million","milliard","trillion"};
    public final String[] tensNames = {
            "", " ten", " dvát-sat’", " tréet-sat’", " trée-tsat’", " peet-dee-syát",
            " sheest-dee-syát", "syém’-dee-syát", " vó-seem-dee-syát",
            " dee-vee-nós-ta"
    };




    public final String[] numNames = {
            "",
            " a-déen", " dva", " trée", " chee-tý-rye", " pyat", " six", " sem", " vó-seem",
            " dyé-veet", " dyé-seet", " a-dée-nat-sat", " dvee-nát-sat", " try-nát-sat",
            " chee-týr-nat-sat", " peet-nát-sat", " shees-nat-sat", " seem-ná-tsat",
            " va-seem-nát-sat", " dee-vet-nát-sat"
    };
    public RUSSIANNumConverter() {}

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
        return numNames[number] + " sotnya" + soFar;
    }


    public  String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "nul"; }

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
            tradBillions = convertLessThanOneThousand(billions) + " milliard ";
        }
        String result =  tradBillions;

        String tradMillions;
        if (millions == 0) {
            tradMillions = "";
        } else {
            tradMillions = convertLessThanOneThousand(millions) + " million ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "a-déen tysyacha ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " tysyacha ";
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
