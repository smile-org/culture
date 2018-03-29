package com.kst.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateUtil {

    public static String formatDateTime(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(d);
        return result;
    }

    public static String formatDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(d);
        return result;
    }

    public static Date dateParse(String dateString)
            throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Date dateTimeParse(String dateTimeString)
            throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateTimeString);
            return date;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Date addOneDay(String date) throws Exception {
        try {
            Date endDate = dateParse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(endDate);
            cl.add(Calendar.DAY_OF_MONTH, 1);
            return cl.getTime();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String generateUTCTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }
}
