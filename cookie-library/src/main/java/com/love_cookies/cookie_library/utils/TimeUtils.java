package com.love_cookies.cookie_library.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xiekun on 2016/03/31.
 *
 * 时间格式化工具类
 *
 * 时间显示规则：
 *
 * 1. 当天时间显示 5分钟之内显示——刚刚
 *    5~60分钟显示——x分钟前（5分钟前）
 *    超过60分钟显示——小时:分钟（15:30）
 *
 * 2. 昨天时间显示——昨天 小时:分钟（昨天 15:30）
 *
 * 3. 当年非最近两天时间显示——月-日 小时:分钟（07-05 18:46）
 *
 * 4. 非当年时间显示——年-月-日 小时:分钟（2013-02-22 13:21）
 */
public class TimeUtils {

    private static int MILL_MIN = 1000 * 60;// 1分钟
    private static int MILL_HOUR = MILL_MIN * 60;// 1小时
    private static int MILL_DAY = MILL_HOUR * 24;// 1天

    private static String JUST_NOW = "刚刚";
    private static String MIN = "分钟前";
    private static String HOUR = "小时前";

    private static String YESTER_DAY = "昨天";

    private static Calendar msgCalendar = null;
    private static SimpleDateFormat dayFormat = null;
    private static SimpleDateFormat dateFormat = null;
    private static SimpleDateFormat yearFormat = null;

    public static String getListTime(long time) {
        long now = System.currentTimeMillis();
        long msg = time;

        Calendar nowCalendar = Calendar.getInstance();
        if (msgCalendar == null) {
            msgCalendar = Calendar.getInstance();
        }

        msgCalendar.setTimeInMillis(time);
        long calcMills = now - msg;
        long calSeconds = calcMills / 1000;

        //5分钟内，展示刚刚更新；
        if (calSeconds < 60 * 5) {
            return JUST_NOW;
        }

        long calMins = calSeconds / 60;
        //一小时内，展示XX分钟前；
        if (calMins < 60) {
            return new StringBuilder().append(calMins).append(MIN).toString();
        }

        long calHours = calMins / 60;
        // 超过一小时小于一天的，展示X小时前
        if (calHours < 24 && isSameDay(nowCalendar, msgCalendar)) {
            return new StringBuilder().append(calHours).append(HOUR).toString();
        }

        long calDay = calHours / 24;
        //超过一天小于两天的，展示昨天；
        if (calDay < 2) {
            return "昨天";
        }
        //超过两天小于三天的，展示前天；
        if (calDay < 3) {
            return "前天";
        }
        //大于三天小于十天的，展示X天前；
        if (calDay < 10) {
            return new StringBuilder().append(calDay).append("天前").toString();
        }

        long calMonth = calDay / 31;
        //大于十天但当年的只展示日月MM-DD
        if (calMonth < 12 && isSameYear(nowCalendar, msgCalendar)) {
            dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
            String result = dateFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(result).toString();
        }

        //非当年的直接展示年月日YYYY-MM-DD
        yearFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String result = yearFormat.format(msgCalendar.getTime());
        return new StringBuilder().append(result).toString();

    }

    public static String getActivityTimePeriod(long begin, long end) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        builder.append(timeFormat.format(new Date(begin))).append("~").append(timeFormat.format(new Date(end)));
        return builder.toString();
    }

    private static boolean isSameHalfDay(Calendar now, Calendar msg) {
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int msgHOur = msg.get(Calendar.HOUR_OF_DAY);

        if (nowHour <= 12 & msgHOur <= 12) {
            return true;
        } else if (nowHour >= 12 & msgHOur >= 12) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSameDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return nowDay == msgDay;
    }

    private static boolean isYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 1;
    }

    private static boolean isTheDayBeforeYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 2;
    }

    private static boolean isSameYear(Calendar now, Calendar msg) {
        int nowYear = now.get(Calendar.YEAR);
        int msgYear = msg.get(Calendar.YEAR);

        return nowYear == msgYear;
    }

}
