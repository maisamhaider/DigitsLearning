package com.example.digitslearning.languagesutils;

import com.example.digitslearning.annotations.MAnnotation;
import com.example.digitslearning.interfaces.Language;

import java.text.DecimalFormat;

public class CHINESENumConverter implements Language {

    public String[] sNum = new String[]{"líng", "Bǎi", "qian", "Bǎi wàn", "yì", "Zhào"};

    public final String[] tensNames = {
            "", " yi-shi", " er-shi", " san-shi", " si-shi", " wu-shi", " liu-shi", " qi-shi",
            " ba-shi", " jiu-shi",
    };
    public final String[] tensInChinese = {
            "", " 十", " 二十", " 三十", " 四十", " 五十", " 六五", " 七六",
            " 八十", " 九十",
    };

    public final String[] numNames = {
            "", " yi", " er", " san", " si", " wu", " liu", " qi", " ba", " jiu", " shi", " shi-yi",
            " shi-er", " shi-san", " shi-si", " shi-wu", " shi-liu", " shi-qi", " shi-ba", " shi-jiu"
    };
    public final String[] numNamesInChinese = {
            "", " 一", " 二", " 三", " 四", " 五", " 六", " 七", " 八", " 九", " 十", " 十一",
            " 十二", " 十三", " 十四", " 十五", " 十六", " 十七", " 十八", " 十九"
    };

    public CHINESENumConverter() {
    }

    public String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + "-Bǎi " + soFar;
    }


    //return chinese numbers in english written form
    public String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "líng";
        }

        String snumber = Long.toString(number);
        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions) + "-yì ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions) + "-yì ";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions) + " Bǎi wàn ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions) + " Bǎi wàn ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "yi-qian ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + "-qian ";
        }
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

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
