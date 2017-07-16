package com.weeho.petim.lib.utils;

import android.text.SpannableString;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 将时间戳转换成，今天，明天，后天
 */
public class TimeToDateUtil {
    private static long jintian;
    /**
     * 日历实例
     */
    private static Calendar c = Calendar.getInstance();

    /**
     * @return
     */
    public static Integer getDateTime4PHP() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取指定数字的天的秒数
     *
     * @return
     */
    public static Integer getDateTime4PHPByNum(int num) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, num);// +1明天 +2后天 -1昨天 -2前天 等等
        // System.out.println("昨天是："+c.getTime());
        return (int) (c.getTimeInMillis() / 1000);
    }

    /**
     * @param date
     * @return
     */
    public static long getDateTime4PHP(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * @param num
     * @return
     */
    public static String getdatatime4PHP(int num) {
        return getDate(new Date(num * 1000L));
    }

    /**
     * @return
     */
    public static String getOrderSnByTime() {
        Date now = new Date();
        String ymd = getDateFormat(now, "yyMMdd");
        String time = String.valueOf(now.getTime());
        time = time.substring(time.length() - 5);
        String microtime = String.valueOf(now.getTime()).substring(11, 13);
        return ymd + time + microtime + (new Random().nextInt(900) + 100);
    }

    /**
     * 获取若干分钟之后的时间对象
     *
     * @param min
     * @return
     */
    public static Date someMinAfter(int min) {
        c.setTime(new Date());
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    /**
     * 若干分钟之后的时间
     */
    public static int someMinAfter(Date date, int min) {
        c.setTime(date);
        c.add(Calendar.MINUTE, min);
        return (int) (c.getTimeInMillis() / 1000);
    }

    /**
     * @return
     */
    public static String getDate() {
        return getDate(new Date());
    }

    /**
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        return getDateFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param format
     * @return
     */
    public static String getDateFormat(String format) {
        return getDateFormat(new Date(), format);
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static String getDateFormat(Date date, String format) {
        String time = new SimpleDateFormat(format).format(date);
        return time;
    }

    /**
     * @param num
     * @return 时间类型
     */
    public static Date getDatefromTime(int num) {
        return new Date(num * 1000L);
    }

    public static Integer getDate4PHP() {
        Date date = new Date();
        Date myFormatter;
        Integer intDate = 0;
        try {
            myFormatter = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(getDateFormat(date, "yyyy-MM-dd"));
            Long dateLong = getDateTime4PHP(myFormatter);
            intDate = dateLong.intValue();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return intDate;
    }

    /**
     * @param dateTime
     * @return Integer 返回类型
     * @throws
     * @Title: getDate4PHP
     * @Description: 获得日期00:00:00
     */
    public static Integer getDate4PHP(Integer dateTime) {
        Date date = new Date(dateTime * 1000L);
        Date myFormatter;
        Integer intDate = 0;
        try {
            myFormatter = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(getDateFormat(date, "yyyy-MM-dd"));
            Long dateLong = getDateTime4PHP(myFormatter);
            intDate = dateLong.intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return intDate;
    }

    public static Integer getCurrentDate() {
        Integer current = (int) (System.currentTimeMillis() / 1000L);
        return current;
    }

    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String compareWithCurrentDate(long startTime, long endTime) {
        Date nowdate = new Date();
        String nowdateString = TimeToDateUtil.getDateFormat(nowdate, "yyyy-MM-dd");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = sf.format(new Date(startTime * 1000L));
        String endDateString = sf.format(new Date(endTime * 1000L));
        String Flag = "0";
        if (nowdateString.compareTo(endDateString) > 0) {
            Flag = "2";// 过期了
        } else if ((nowdateString.compareTo(startDateString) < 0)) {
            Flag = "0";// 未使用
        } else {
            Flag = "1";// 使用中
        }
        return Flag;
    }

    public static Boolean compareTimeWithAfterTwenty(int addTime) {
        // long addTime =1415002939;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = sf.format(new Date(addTime * 1000L));
        Date date = TimeToDateUtil.StringToDate(timeString, "yyyy-MM-dd HH:mm:ss");
        // System.out.println("验证码日期："+time_yesterday);

        long time = date.getTime() + 20 * 60 * 1000;
        Date dateTw = new Date(time);
        // System.out.println("20分钟后的日期："+time_after);
        Date datenow = new Date();
        // System.out.println("当前日期："+datenow);
        // System.out.println(datenow.compareTo(dateTw));
        // System.out.println(dateTw.compareTo(datenow));
        if (dateTw.compareTo(datenow) == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param @param  dateTime
     * @param @param  time
     * @param @return 设定文件
     * @return Integer 返回类型
     * @throws
     * @Title: getIntervalTime
     * @Description: 倒计时时间间隔
     */
    public static Integer getIntervalTime(Integer dateTime, String time) {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String yyyyMMdd = spf.format(new Date(dateTime * 1000l));
        StringBuffer sb = new StringBuffer(yyyyMMdd);
        sb.append(" ");
        sb.append(time.split("-")[0]);
        sb.append(":00");
        String yyyyMMddHHmmss = sb.toString();
        Date takeDate = null;
        try {
            takeDate = sp.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) (takeDate.getTime() / 1000) - TimeToDateUtil.getDateTime4PHP();
    }

    public static int compare_date(String CurrentDate, String DATE2) {
        // 日期格式 2008-05-22
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date nowDate = df.parse(CurrentDate);
            Date dt2 = df.parse(DATE2);
            if (nowDate.getTime() > dt2.getTime()) {// 比现在日期小== 今天之前的日期
                // System.out.println("nowDate 在dt2前");
                return 1;
            } else if (nowDate.getTime() < dt2.getTime()) {// 比现在日期大==明后天
                // System.out.println("nowDate在dt2后");
                return -1;
            } else {// 同一天
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compare_date_min(String CurrentDate, String DATE2) {
        // 日期格式 2008-05-22
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date nowDate = df.parse(CurrentDate);
            Date dt2 = df.parse(DATE2);
            if (nowDate.getTime() > dt2.getTime()) {// 比现在日期小== 今天之前的日期
                // System.out.println("nowDate 在dt2前");
                return 1;
            } else if (nowDate.getTime() < dt2.getTime()) {// 比现在日期大==明后天
                // System.out.println("nowDate在dt2后");
                return -1;
            } else {// 同一天
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String getDates(long time) {
        // Date date = new Date();
        // long dayStamp = 24 * 60 * 60;
        // int times = date.getSeconds();
        // int times = (int) date.getTime()/1000;
        int times = (int) (System.currentTimeMillis() / 1000);
        jintian = TimeToDateUtil.getDate4PHP(times);
        // SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long mingtian = TimeToDateUtil.getDate4PHP(TimeToDateUtil
                .getDateTime4PHPByNum(+1));
        long houtian = TimeToDateUtil
                .getDate4PHP(TimeToDateUtil.getDateTime4PHPByNum(+2));
        ;
        long dahoutian = TimeToDateUtil.getDate4PHP(TimeToDateUtil
                .getDateTime4PHPByNum(+3));
        // LogUtil.d(TAG, "jintian:" + sdf2.format(new Date(jintian * 1000)));
        // LogUtil.d(TAG, "mingtian:" + sdf2.format(new Date(mingtian * 1000)));

        // LogUtil.d(TAG, "houtian:" + sdf2.format(new Date(houtian * 1000)));
        // LogUtil.d(TAG, "dahoutian:" + sdf2.format(new Date(dahoutian *
        // 1000)));
        // List<String> dates = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("M月d日");

        String str = null;
        // 今天
        if (jintian <= time && time < mingtian) {
            str = "今天";
            // 明天
        } else if (mingtian <= time && time < houtian) {
            str = "明天";
            // 后天
        } else if (houtian <= time && time < dahoutian) {
            str = "后天";

        } else {
            Date date1 = new Date(time * 1000);
            str = sdf.format(date1);
        }
        return str;
    }

    public static  CharSequence timeToDate(long mills) {
        SpannableString spannableString = null;
        String result = getDates(mills / 1000L);
        if (result.equals("今天")) {
            return spannableString;
        } else if (result.equals("明天")) {
            return spannableString;
        } else if (result.equals("后天")) {
            return spannableString;
        } else {
            return "";
        }
    }
}
