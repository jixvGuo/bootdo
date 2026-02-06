package com.bootdo.cpe.utils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author houzb
 * @Description
 * @create 2020-10-10 2:05
 */
public class NumberUtils {

    public static double getAvg(List<Double> list){
        double total = 0;
        if(list.size() < 3) {
            for(double v:list) {
                total += v;
            }
            double rst = total / list.size();
            return keepDecimalRunding(rst);
        }
        list.sort((((o1, o2) -> (int) (o2 * 100 - o1 * 100))));
        for(int i=1;i<list.size() - 1;i++) {
            total += list.get(i);
        }
        double rst = total / (list.size() - 2);
        return keepDecimalRunding(rst);
    }

    public static double keepDecimalRunding(double rst) {
        BigDecimal bd = new BigDecimal(rst);
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd2.doubleValue();
    }

    public static boolean isNumber(String str) {
        //采用正则表达式的方式来判断一个字符串是否为数字，这种方式判断面比较全
        //可以判断正负、整数小数

        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();

        return isInt || isDouble;

    }

}
