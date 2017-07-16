package com.weeho.petim.lib.utils;

import java.text.DecimalFormat;

/**
 * 数字格式化工具类
 */
public class NumberUtils {
    public static String formatFloatFen(float val) {
        DecimalFormat fnum = new DecimalFormat("0.0");
        String str = fnum.format(val);
        return str;
    }

    /**
     * 判断整型 或 浮点型 小数点后是否全为0
     */
    public static boolean isInt(double f) {
        String s = String.format("%#.1f", f);
        int len = s.length();
        if (len > 16) {
            throw new IllegalArgumentException("长度不能超过16位");
        }
        char end = s.charAt(len - 1);
        if (s.charAt(len - 2) == end && end == '0') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断整型 或 浮点型后  返回对应的数值
     */
    public static String parse(double f) {
        if (NumberUtils.isInt(f)) {
            return String.valueOf((int) f);
        } else {
            return String.format("%#.2f", f);
        }
    }

    /**
     * 将浮点型 转成  带一位小数形式
     *
     * @param s
     * @return
     */
    public static String parse(String s) {
        s = String.format("%.1f", Double.parseDouble(s));
        if (s.equals("0.0")) {
            return "0.1";
        }
        return s;
    }
}
