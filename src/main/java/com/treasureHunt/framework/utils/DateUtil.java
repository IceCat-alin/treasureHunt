package com.treasureHunt.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author linying
 * @version v1.0.0
 * @description 日期时间工具
 * @date 2018-04-12 14:44:02
 * @modified By
 */
public class DateUtil {

    /**
     * 日志打印
     */
    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    /**
     * @description 将Date类型转换为字符串yyyy-MM-dd HH:mm:ss
     * @param date 日期
     * @return format date
     */
    public static String formatDatetimeToStr(Date date) {
        return formatDateToStrPattern(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @description 将Date类型转换为字符串HH:mm:ss
     * @param date 日期
     * @return format date
     */
    public static String formatDateToStrhms(Date date) {
        return formatDateToStrPattern(date, "HH:mm:ss");
    }

    /**
     * @description 将Date类型转换为字符串yyyy-MM-dd
     * @param date 日期
     * @return format date
     */
    public static String formatDateToStr(Date date) {
        return formatDateToStrPattern(date, "yyyy-MM-dd");
    }

    /**
     * @description 将Date类型转换为字符串MM-dd HH:mm:ss
     * @param date 日期
     * @return format date
     */
    public static String formatDatetimeT2Str(Date date) {
        return formatDateToStrPattern(date, "MM-dd HH:mm:ss");
    }

    /***
     * @description 将具体时间点转化为定时任务将要执行的时间格式,eg. "0 06 10 15 1 ? 2014"
     * @param date 时间点
     * @return 任务执行的日期格式
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateToStrPattern(date, dateFormat);
    }

    /**
     * @description 将Date类型转换为字符串
     * @param date 日期
     * @param pattern 字符串格式
     * @return format date
     */
    public static String formatDateToStrPattern(Date date, String pattern) {
        if (date == null) {
            return "null";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @description 将字符串转换为Date类型
     * @param date 日期
     * @return format date
     */
    public static Date formatStrToDateCommon(String date) {
        return formatStrToDatePattern(date, null);
    }

    /**
     * @description 将字符串转换为Date类型 yyyy-MM-dd HH:mm:ss
     * @param date 日期
     * @param pattern 格式
     * @return format date
     */
    public static Date formatStrToDatePattern(String date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (StringUtils.isBlank(date)) {
            return new Date();
        }
        Date d = null;
        try {
            d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException pe) {
        }
        return d;
    }

    /**
     * @description 获得当前日期
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * @description 获得当前日期
     * @return yyyy-MM-dd 00:00:00
     */
    public static Date getCurrentDateBegin() {
        return formatStrToDatePattern(formatDateToStr(new Date()) + " 00:00:00", null);
    }

    /**
     * @description 获得当前日期
     * @return yyyy-MM-dd 23:59:59
     */
    public static Date getCurrentDateEnd() {
        return formatStrToDatePattern(formatDateToStr(new Date()) + " 23:59:59", null);
    }

    /**
     * @description 获取前一天的开始时间
     * @param date 时间
     * @return 前一天的开始时间
     */
    public static Date getBeforeDateBegin(Date date) {
        date = formatStrToDatePattern(formatDateToStr(date) + " 00:00:00", null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @description 获取前一天的结束时间
     * @param date 时间
     * @return 前一天的结束时间
     */
    public static Date getBeforeDateEnd(Date date) {
        date = formatStrToDatePattern(formatDateToStr(date) + " 23:59:59", null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @description 计算当前年月的天数
     * @param dyear 年份
     * @param dmouth 月份
     * @return 天数
     */
    public static int calDayByYearAndMonth(String dyear, String dmouth) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
        Calendar rightNow = Calendar.getInstance();
        try {
            rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @description 字符串转日期
     * @param str 日期字符串
     * @return yyyy-MM-dd
     */
    public static Date getDateFromString(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @description 获得某个时间的下一天
     * @param dateStr 日期字符串
     * @return format date
     */
    public static String getLastDayOfTime(String dateStr) {
        Date afterDate = null;
        Date toEndDate = getDateFromString(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toEndDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        afterDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String afterDate2 = sdf.format(afterDate);
        return afterDate2;
    }

    /**
     * @description 获得某个时间的下一天
     * @param date 日期
     * @return format date
     */
    public static Date getLastDayOfTime(Date date) {
        Date afterDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        afterDate = calendar.getTime();
        return afterDate;
    }

    /**
     * @description 计算相隔天数
     * @param d1 起始日期
     * @param d2 结束日期
     * @return int
     * @throws Exception 系统异常
     */
    public static int dateDiff(Date d1, Date d2) throws Exception {
        long n1 = d1.getTime();
        long n2 = d2.getTime();
        long diff = Math.abs(n1 - n2);
        diff /= 3600 * 1000 * 24;
        return (int) diff;
    }

    /**
     * @description 计算相隔小时数
     * @param d1 起始日期
     * @param d2 结束日期
     * @return int
     * @throws Exception 系统异常
     */
    public static int hourDiff(Date d1, Date d2) throws Exception {
        long n1 = d1.getTime();
        long n2 = d2.getTime();
        long diff = Math.abs(n1 - n2);
        diff /= 3600 * 1000;
        return (int) diff;
    }

    /**
     * @description 根据当月的yyyy-MM获得下个月的yyy-MM
     * @param currentDate 当前年月
     * @return format date
     */
    public static String getNextMonthDate(String currentDate) {
        if (currentDate == null) {
            return "";
        }
        String[] stringdate = currentDate.split("-");
        int year = Integer.valueOf(stringdate[0]);
        int month = Integer.valueOf(stringdate[1]) + 1;
        String monthString = month + "";
        if (month > 12) {
            year = year + 1;
            month = 1;
        }
        if (month < 10) {
            monthString = "0" + month;
        }
        return year + "-" + monthString;
    }

    /**
     * @description 获得某个月的最后一天
     * @param date 日期
     * @return format date
     */
    public static String getLastDayOfMonthDate(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(cDay.getTime());
    }

    /**
     * @description 获得某个月的第一天
     * @param date 日期
     * @return format date
     */
    public static String getFirstDayOfMonthDate(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(cDay.getTime());
    }

    /**
     * 
     * @description 将字符串转日期
     * @param str 日期字符串
     * @return format date
     */
    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;

    }

    /**
     * @description 获得昨天的日期
     * @param dateStr 日期
     * @return format date
     */
    public static String getLastDayStr(String dateStr) {
        Date afterDate = null;
        Date toEndDate = getDateFromString(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toEndDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        afterDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String afterDate2 = sdf.format(afterDate);
        return afterDate2;
    }

    /**
     * @description 获得上一个月
     * @param dateStr 日期
     * @return format date
     */
    public static String getYesMonthStr(String dateStr) {
        Date afterDate = null;
        Date toEndDate = getDateFromString(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toEndDate);
        calendar.add(Calendar.MONTH, -1);
        afterDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String afterDate2 = sdf.format(afterDate);
        return afterDate2;
    }

    /**
     * @description 获得上一年
     * @param dateStr 日期
     * @return format date
     */
    public static String getLastYearStr(String dateStr) {
        Date afterDate = null;
        Date toEndDate = getDateFromString(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toEndDate);
        calendar.add(Calendar.YEAR, -1);
        afterDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String afterDate2 = sdf.format(afterDate);
        return afterDate2;
    }

    /**
     * @description 获得当前月第一天
     * @return format date yyyyMMdd
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String dayFirst = df.format(gcLast.getTime());
        return dayFirst;
    }

    /**
     * @description 指定时间的下n年同一天
     * @param dateStr 指定时间
     * @param n n年后
     * @return 指定时间的下i年同一天
     */
    public static String getNextYearTime(String dateStr, int n) {
        String d1 = dateStr.substring(0, 4);
        Integer id1 = Integer.valueOf(d1);
        Integer id2 = id1 + n;
        String nd1 = id2.toString();
        String d2 = dateStr.substring(4, 8);
        String dn = nd1 + d2;
        return dn;
    }

    /**
     * @description 两个日期大小比较(dt1 在dt2前 返回-1，之后返回1 相等返回0)
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {// 相等
            return 0;
        }
    }

    /**
     * @description 日期数字
     */
    private static int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * @description 判断日期格式是否正确
     * @param date yyyy-MM-dd HH:mm:ss
     * @return 是否正确格式
     */
    public static boolean isValidDate(String date) {
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            if (year <= 0) {
                return false;
            }
            int month = Integer.parseInt(date.substring(5, 7));
            if (month <= 0 || month > 12) {
                return false;
            }
            int day = Integer.parseInt(date.substring(8, 10));
            if (day <= 0 || day > DAYS[month]) {
                return false;
            }
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
                return false;
            }
            int hour = Integer.parseInt(date.substring(11, 13));
            if (hour < 0 || hour > 23) {
                return false;
            }
            int minute = Integer.parseInt(date.substring(14, 16));
            if (minute < 0 || minute > 59) {
                return false;
            }
            int second = Integer.parseInt(date.substring(17, 19));
            if (second < 0 || second > 59) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @description 判断日期格式是否正确
     * @param year year
     * @return 判断日期格式是否正确
     */
    private static boolean isGregorianLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * @description msMatrixingDateTime(这里用一句话描述这个方法的作用)
     * @param ms 毫秒
     * @return 时间
     */
    public static String msMatrixingDateTime(Long ms) {
        String dateNum = "";
        if (ms != null && ms > 0) {
            Long totalss = ms / 1000;
            // 一天等于86400秒
            Long ssDay = 86400L;
            Long day = totalss / ssDay;
            Long remainderDay = totalss % ssDay;
            Long remainderHours = 0L;
            Long hours = 0L;
            Long minute = 0L;
            Long ss = 0L;
            if (day > 0) {
                dateNum += day + "天";
            }
            if (remainderDay > 0) {
                hours = remainderDay / 3600;// 一小时3600s
                remainderHours = remainderDay % 3600;
                if (hours > 0) {
                    dateNum += hours + "小时";
                }
                if (remainderHours > 0) {
                    minute = remainderHours / 60;// 一分钟等于60秒
                    if (minute > 0) {
                        dateNum += minute + "分";
                    }
                    ss = remainderHours % 60;
                    if (ss > 0) {
                        dateNum += ss + "秒";
                    }
                }
            }
        }
        return dateNum;
    }
}
