package com.example.digitslearning.languagesutils;

import com.example.digitslearning.annotations.MAnnotation;
import com.example.digitslearning.interfaces.Language;

import java.text.DecimalFormat;

public class SPANISHNumConverter  implements Language {
    public String[] sNum = new String[]{"cero","ciento","cheon","millón","mil millones",
            "billón"};
    public final String[] tensNames = {
            "", " diez", " veinte", " treinta", " cuarenta", " cincuenta", " sesenta", " setenta",
            " ochenta",
            " noventa"
    };


    public final String[] numNames = {
            "", " uno", " dos", " tres", " cuatro", " cinco", " seis", " siete", " ocho", " nueve",
            " diez", " once", " doce", " trece", " catorce", " quince", " dieciséis", " diecisiete",
            " dieciocho",
            " diecinueve"
    };

    public SPANISHNumConverter() {}

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
        return numNames[number] + " ciento" + soFar;
    }


    public  String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "cero"; }

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
                tradBillions = convertLessThanOneThousand(billions) + "mil millones ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions) + " millónes ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions) + " un millón ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions) + " millón ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "uno mil ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " mil ";
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
