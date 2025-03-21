package com.cc.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qf.xia
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyyMMdd
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * HH:mm:ss
     */
    public static final String HHMMSS = "HH:mm:ss";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 没有连接线的日期时间格式
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 没有连接线的日期时间格式
     */
    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";

    /**
     * 月份在前的日期格式
     */
    public static final String SINGLE_DATE_FORMAT = "MM-dd-yyyy";

    /**
     * 根据传入的格式将日期对象转成对应格式的日期字符串
     *
     * @param date   日期对象
     * @param format 日期格式
     * @return 转换后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        if ((date == null) || StringUtils.isBlank(format)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 根据传入的格式将日期字符串转成日期对象
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return 转换后的日期对象
     */
    public static Date toDate(String dateStr, String format) {
        if (StringUtils.isAnyBlank(dateStr, format)) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(dateStr, pos);
    }

    /**
     * 添加日期
     *
     * @param srcDate 原始时间
     * @param days  需要增加的天数。
     * @return
     */
    public static Date add(Date srcDate, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(srcDate);
        c.add(Calendar.DAY_OF_MONTH, days);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 获取当前小时数
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 拿到10位的时间戳
     * @return
     */
    public static long getTimeStampNow() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取两个时间之间的差值
     * @param chronoUnit
     * @param startTime
     * @param endTime
     * @return
     */
    public static long between(ChronoUnit chronoUnit, Date startTime, Date endTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        Instant startInstant = startTime.toInstant();
        LocalDateTime startDateTime = startInstant.atZone(zoneId).toLocalDateTime();

        Instant endInstant = endTime.toInstant();
        LocalDateTime endDateTime = endInstant.atZone(zoneId).toLocalDateTime();

        return chronoUnit.between(startDateTime, endDateTime);
    }
}
