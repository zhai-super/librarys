package com.zyh.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//时间工具类
public class Time {
    public static Date currentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        System.out.println(currentTime);
        return date;
    }

    public static String borrowEndTime(int day) {
        //借阅时时间不能超过60天,不能为0天,
        if (day > 60 || day <= 0 ) {
            day = 60;
        }
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.DATE, day);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        return enddate;
    }
}
