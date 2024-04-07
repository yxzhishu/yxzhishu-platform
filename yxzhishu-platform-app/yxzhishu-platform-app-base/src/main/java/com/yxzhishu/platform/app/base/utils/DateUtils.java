package com.yxzhishu.platform.app.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/9 10:49
 * @remark:
 */
public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowDate(){
        return new Date();
    }

    /**
     * 获取日期当天
     * @param date
     * @return
     */
    public static Date getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天
     * @return
     */
    public static Date getToday(){
        return getToday(getNowDate());
    }

    /**
     * 获取每月第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取每月第一天
     * @param
     * @return
     */
    public static Date getFirstDayOfMonth() {
        return getFirstDayOfMonth(getNowDate());
    }

    /**
     * 获取周数
     * @return
     */
    public static int getWeek() {
        return getWeek(getNowDate());
    }

    /**
     * 获取周数
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week == 1 ? 7 : week - 1;
    }

    /**
     * 字符串格式转时间
     * @param d yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDt(String d) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 时间增减
     * @param date
     * @param unit
     * @param num
     * @return
     */
    public static Date dateAddSub(Date date, int unit, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num);
        return calendar.getTime();
    }

    /**
     * 时间增减天数
     * @param date
     * @param num
     * @return
     */
    public static Date dateAddSubDay(Date date, int num) {
        return dateAddSub(date, Calendar.DATE, num);
    }

    /**
     * 时间是否在时间按区间内
     * @param base
     * @param start
     * @param end
     * @return
     */
    public static boolean checkBetween(Date base, Date start, Date end) {
        Date date = getNowDate();
        return date.before(start) && date.after(end);
    }

    /**
     * 当前时间是否在时间按区间内
     * @param start
     * @param end
     * @return
     */
    public static boolean checkBetween(Date start, Date end) {
        return checkBetween(getNowDate(), start, end);
    }
}
