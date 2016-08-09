package com.love_cookies.beauty.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xiekun on 2016/7/25 0025.
 *
 * ISO时间格式转换
 */
public class ISODateUtils {
    /**
     * ISO8601时间转换（正确性待验证）---验证通过，会根据时区添加
     * 2016-02-24T16:00:00.000+0000 ==> timestamp --> 2016-02-24 16:00:00
     *
     * @param iso8601
     * @return
     */
    public static String ISO8601Formater(String iso8601) {
        if (TextUtils.isEmpty(iso8601)) {
            return "";
        }
        String time = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.CHINA);
        try {
            iso8601 = iso8601.replace("Z", " UTC");
            Date date = df.parse(iso8601);
            long millis = date.getTime();

            //2015-01-01 00:00:00
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd\nyyy");
            time = sdf.format(millis);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
