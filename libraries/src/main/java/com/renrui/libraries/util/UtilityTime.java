package com.renrui.libraries.util;

import android.text.TextUtils;

import com.renrui.libraries.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilityTime {

    /**
     * yyyy-MM-dd HH:mm
     */
    public final static SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * yyyy-MM-dd
     */
    public final static SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * yyyy-MM-dd
     */
    public final static SimpleDateFormat sdf_3 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf3));

    /**
     * HH:mm
     */
    public final static SimpleDateFormat sdf_4 = new SimpleDateFormat("HH:mm");

    /**
     * MM-dd HH:mm
     */
    public final static SimpleDateFormat sdf_5 = new SimpleDateFormat("MM-dd HH:mm");

    /**
     * MM月dd日
     */
    public final static SimpleDateFormat sdf_6 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf6));

    /**
     * yyyy年MM月dd日
     */
    public final static SimpleDateFormat sdf_7 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf7));

    /**
     * MM月dd日 HH:mm
     */
    public final static SimpleDateFormat sdf_8 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf8));

    /**
     * yyyy.MM
     */
    public final static SimpleDateFormat sdf_9 = new SimpleDateFormat("yyyy.MM");

    /**
     * yyyy
     */
    public final static SimpleDateFormat sdf_10 = new SimpleDateFormat("yyyy");

    /**
     * MM
     */
    public final static SimpleDateFormat sdf_11 = new SimpleDateFormat("MM");

    /**
     * MM/dd
     */
    public final static SimpleDateFormat sdf_12 = new SimpleDateFormat("yyyyMMdd");

    /**
     * yyyy/MM/dd
     */
    public final static SimpleDateFormat sdf_13 = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * yyyy年MM月dd日 HH:mm
     */
    public final static SimpleDateFormat sdf_14 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf14));

    /**
     * yyyy.MM.dd
     */
    public final static SimpleDateFormat sdf_15 = new SimpleDateFormat("yyyy.MM.dd");
    /**
     * MM月dd日 HH:mm
     */
    public final static SimpleDateFormat sdf_16 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf16));

    /**
     * M.dd HH:mm
     */
    public final static SimpleDateFormat sdf_17 = new SimpleDateFormat("M.dd HH:mm");

    /**
     * M.dd HH:mm
     */
    public final static SimpleDateFormat sdf_18 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf18));

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static SimpleDateFormat sdf_19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM
     */
    public final static SimpleDateFormat sdf_20 = new SimpleDateFormat("yyyy-MM");

    /**
     * MM-dd
     */
    public final static SimpleDateFormat sdf_21 = new SimpleDateFormat("MM-dd");

    /**
     * MM-dd
     */
    public final static SimpleDateFormat sdf_22 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf22));

    private final static String Yesterday = LibrariesCons.getContext().getString(R.string.UtilityTime_Yesterday);
    private final static String Today = LibrariesCons.getContext().getString(R.string.UtilityTime_Today);
    private final static String Tomorrow = LibrariesCons.getContext().getString(R.string.UtilityTime_Tomorrow);

    /**
     * 1分钟毫秒数
     */
    private final static long lMinuteTimes = 1000 * 60;

    /**
     * 1小时毫秒数
     */
    private final static long lSecondTimes = lMinuteTimes * 60;

    /**
     * 1天毫秒数
     */
    private final static long lDayTimes = lSecondTimes * 24;

    private static Date tempDate;
    private static Date tempThisDate = new Date();
    private static Calendar tempCal = Calendar.getInstance();

    public static String getWeek(int day) {
        String week;
        if (day == Calendar.MONDAY) {
            week = "周一";
        } else if (day == Calendar.TUESDAY) {
            week = "周二";
        } else if (day == Calendar.WEDNESDAY) {
            week = "周三";
        } else if (day == Calendar.THURSDAY) {
            week = "周四";
        } else if (day == Calendar.FRIDAY) {
            week = "周五";
        } else if (day == Calendar.SATURDAY) {
            week = "周六";
        } else if (day == Calendar.SUNDAY) {
            week = "周日";
        } else {
            week = "未知数据";
        }
        return week;
    }

    public static String getWeekAllText(int day) {
        String week;
        if (day == Calendar.MONDAY) {
            week = "星期一";
        } else if (day == Calendar.TUESDAY) {
            week = "星期二";
        } else if (day == Calendar.WEDNESDAY) {
            week = "星期三";
        } else if (day == Calendar.THURSDAY) {
            week = "星期四";
        } else if (day == Calendar.FRIDAY) {
            week = "星期五";
        } else if (day == Calendar.SATURDAY) {
            week = "星期六";
        } else if (day == Calendar.SUNDAY) {
            week = "星期日";
        } else {
            week = "未知数据";
        }
        return week;
    }

    public static String getSdfStringByProgrcess(String lTime) {
        String time;

        if (TextUtils.isEmpty(lTime)) {
            time = "";
        } else {
            try {
                UtilityTime.tempDate = new Date(Long.parseLong(lTime));

                // 跨年显示：yyyy-MM-dd HH:mm
                if (UtilityTime.tempDate.getYear() != UtilityTime.tempThisDate.getYear()) {
                    return UtilityTime.sdf_1.format(Long.parseLong(lTime));
                }

                // 今天：今天 HH:mm
                if (UtilityTime.tempDate.getYear() == UtilityTime.tempThisDate.getYear() && UtilityTime.tempDate.getMonth() == UtilityTime.tempThisDate.getMonth() && UtilityTime.tempDate.getDate() == UtilityTime.tempThisDate.getDate()) {
                    return "今天 " + UtilityTime.sdf_4.format(Long.parseLong(lTime));
                }

                // 昨天：昨天 HH:mm
                UtilityTime.tempCal.setTime(UtilityTime.tempDate);
                UtilityTime.tempCal.add(Calendar.DAY_OF_YEAR, 1);
                if (UtilityTime.tempThisDate.getYear() == UtilityTime.tempCal.getTime().getYear() && UtilityTime.tempThisDate.getMonth() == UtilityTime.tempCal.getTime().getMonth() && UtilityTime.tempThisDate.getDate() == UtilityTime.tempCal.getTime().getDate()) {
                    return "昨天 " + UtilityTime.sdf_4.format(Long.parseLong(lTime));
                }

                // 当前年，且非今天和昨天， MM-dd HH:mm
                return UtilityTime.sdf_5.format(Long.parseLong(lTime));
            } catch (Exception ex) {
                time = "";
            }
        }

        return time;
    }

    public static String getSdfStringByFriendRemark(long lTime) {
        String time;


        try {
            UtilityTime.tempDate = new Date(lTime);

            // 今天：今天 HH:mm
            if (UtilityTime.tempDate.getYear() == UtilityTime.tempThisDate.getYear() && UtilityTime.tempDate.getMonth() == UtilityTime.tempThisDate.getMonth() && UtilityTime.tempDate.getDate() == UtilityTime.tempThisDate.getDate()) {
                return "今天 " + UtilityTime.sdf_4.format(lTime);
            }

            // 昨天：昨天 HH:mm
            UtilityTime.tempCal.setTime(UtilityTime.tempDate);
            UtilityTime.tempCal.add(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempThisDate.getYear() == UtilityTime.tempCal.getTime().getYear() && UtilityTime.tempThisDate.getMonth() == UtilityTime.tempCal.getTime().getMonth() && UtilityTime.tempThisDate.getDate() == UtilityTime.tempCal.getTime().getDate()) {
                return "昨天 " + UtilityTime.sdf_4.format(lTime);
            }

            // 当前年，且非今天和昨天， MM-dd HH:mm
            return UtilityTime.sdf_14.format(lTime);
        } catch (Exception ex) {
            time = "";
        }


        return time;
    }

    public static String getSdfString(SimpleDateFormat sdf, String lTime) {
        String time;

        if (sdf == null || TextUtils.isEmpty(lTime)) {
            time = "";
        } else {
            try {
                time = sdf.format(Long.parseLong(lTime));
            } catch (Exception ex) {
                time = "";
            }
        }

        return time;
    }

    /**
     * 获取面试时间
     *
     * @param lTime 时间
     * @return 返回显示文字
     */
    public static String getOfficeInterviewDateString(String lTime) {

        if (TextUtils.isEmpty(lTime)) {
            return "";
        }

        try {
            final String time = UtilityTime.sdf_5.format(Long.parseLong(lTime));

            final String[] split = time.split(" ");
            final String date_last = split[1];
            final String date_pre = UtilityTime.getDayText(lTime);

            String resultDate;

            if (date_pre.equalsIgnoreCase(UtilityTime.Yesterday) || date_pre.equalsIgnoreCase(UtilityTime.Today) || date_pre.equalsIgnoreCase(UtilityTime.Tomorrow)) {
                resultDate = date_pre + "  " + date_last;
            } else {
                resultDate = split[0] + "  (" + date_pre + ")  " + date_last;
            }

            return resultDate;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取职位详情列表的显示规则，1.4.1版本中职位详情页面的面试时间显示规则改变了
     *
     * @param lTime     时间
     * @param ivDateEnd ivDateEnd
     * @return 返回显示文字
     */
    public static String getOfficeInterviewDateString(String lTime, String ivDateEnd) {

        if (TextUtils.isEmpty(lTime)) {
            return "";
        }

        try {
            final String time = UtilityTime.sdf_5.format(Long.parseLong(lTime));

            final String[] split = time.split(" ");
            final String date_last = split[1];
            final String date_pre = getDayText(lTime);

            String resultDate;

            if (TextUtils.isEmpty(ivDateEnd)) {
                resultDate = split[0] + "  (" + date_pre + ")  " + date_last;
            } else {
                String endTime = UtilityTime.sdf_4.format(new Date(Long.parseLong(ivDateEnd)));
                if (endTime.equals(date_last)) {
                    resultDate = split[0] + "  (" + date_pre + ")  " + date_last;
                } else {
                    resultDate = split[0] + "  (" + date_pre + ")  " + date_last + "-" + endTime;
                }
            }
            return resultDate;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取申请面试弹窗里的面试时间
     */
    public static String getInterViewDateForDialog(String ivDate) {

        if (TextUtils.isEmpty(ivDate)) {
            return "";
        }

        try {
            return UtilityTime.sdf_1.format(Long.parseLong(ivDate));
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取申请面试弹窗里的面试时间
     */
    public static String getInterViewDateForDialog(long startTime, long endTime) {

        try {
            Calendar calStart = Calendar.getInstance();
            calStart.setTimeInMillis(startTime);

            if (endTime == 0) {
                return String.format("%s(%s)%s", sdf_6.format(startTime), getDayText(startTime + ""), sdf_4.format(startTime));
            } else {
                return String.format("%s(%s)%s-%s", sdf_6.format(startTime), getDayText(startTime + ""), sdf_4.format(startTime), sdf_4.format(endTime));
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getAlarmDateString(String lTime) {

        if (TextUtils.isEmpty(lTime)) {
            return "";
        }

        try {
            final String time = UtilityTime.sdf_8.format(Long.parseLong(lTime));

            final String[] split = time.split(" ");
            final String date_last = split[1];
            final String date_pre = getAlarmText(lTime);

            return split[0] + "  (" + date_pre + ")  " + date_last;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getAlarmDateStringXcSay(String lTime) {

        if (TextUtils.isEmpty(lTime)) {
            return "";
        }

        try {
            final String time = UtilityTime.sdf_8.format(Long.parseLong(lTime));

            final String[] split = time.split(" ");
            final String date_last = split[1];
            final String date_pre = getAlarmText(lTime);

            return split[0] + " " + date_pre + " " + date_last;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getAlarmText(String lTime) {

        try {
            UtilityTime.tempCal.setTimeInMillis(Long.parseLong(lTime));

            final Calendar calThis = Calendar.getInstance();
            calThis.setTimeInMillis(System.currentTimeMillis());

            // 当天
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "今天";
            }

            // 明天
            calThis.roll(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "明天";
            }

            // 后天
            calThis.roll(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "后天";
            }

            return UtilityTime.getWeek(UtilityTime.tempCal.get(Calendar.DAY_OF_WEEK));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getDayText(String lTime) {

        try {
            UtilityTime.tempCal.setTimeInMillis(Long.parseLong(lTime));

            final Calendar calThis = Calendar.getInstance();
            calThis.setTimeInMillis(System.currentTimeMillis());

            // 当天
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "今天";
            }

            // 昨天
            calThis.roll(Calendar.DAY_OF_YEAR, -1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "昨天";
            }

            // 明天
            calThis.roll(Calendar.DAY_OF_YEAR, 2);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return "明天";
            }

            return UtilityTime.getWeek(UtilityTime.tempCal.get(Calendar.DAY_OF_WEEK));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取进展列表展示时间
     */
    public static String getShowProcessDateTime(String valuel) {

        if (TextUtils.isEmpty(valuel)) {
            return "";
        }

        try {
            return getShowProcessDateTime(Long.parseLong(valuel));
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取进展列表展示时间
     */
    public static String getShowProcessDateTime(Long valuel) {

        if (valuel <= 0) {
            return "";
        }

        try {
            UtilityTime.tempDate = new Date(valuel);
            final Date thisDate = new Date();

            // 跨年格式：yyyy-MM-dd HH:mm
            if (UtilityTime.tempDate.getYear() != thisDate.getYear()) {
                return UtilityTime.sdf_1.format(UtilityTime.tempDate);
            }

            // 当天： 今天 MM-dd
            if (UtilityTime.tempDate.getYear() == thisDate.getYear() && UtilityTime.tempDate.getMonth() == thisDate.getMonth() && UtilityTime.tempDate.getDate() == thisDate.getDate()) {
                return "今天 " + UtilityTime.sdf_4.format(UtilityTime.tempDate);
            }

            Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(UtilityTime.tempDate);
            calendar.roll(Calendar.DAY_OF_YEAR, 1);
            // 当天： 昨天 MM-dd
            if (calendar.getTime().getYear() == thisDate.getYear() && calendar.getTime().getMonth() == thisDate.getMonth() && calendar.getTime().getDate() == thisDate.getDate()) {
                return "昨天 " + UtilityTime.sdf_4.format(UtilityTime.tempDate);
            }

            return UtilityTime.sdf_5.format(UtilityTime.tempDate);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取和当前时间间隔差的展示text
     */
    public static String getActiveTime(long value) {
        String text = "";

        try {
            long intervalTime = System.currentTimeMillis() - value;

            if (intervalTime <= (lSecondTimes * 12)) {
                text = "刚刚";
            } else if (intervalTime <= lDayTimes) {
                text = "一天内";
            } else if (intervalTime <= (lDayTimes * 2)) {
                text = "两天内";
            } else if (intervalTime <= (lDayTimes * 3)) {
                text = "三天内";
            } else if (intervalTime <= (lDayTimes * 7)) {
                text = "一周内";
            } else if (intervalTime <= (lDayTimes * 14)) {
                text = "两周内";
            } else if (intervalTime <= (lDayTimes * 30)) {
                text = "一个月内";
            } else {
                text = "一个月+";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return text;
    }

    /**
     * 获取某年某月中的最大天数
     *
     * @param year  年
     * @param month 月
     * @return 月中的天数
     */
    public static int getDays(int year, int month) {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month - 1);

        return now.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取两个日期之间的间隔天数
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);
        // 如果是0,默认当前时间
        if (fromCalendar.get(Calendar.YEAR) <= 1970) {
            fromCalendar.setTimeInMillis(System.currentTimeMillis());
        }

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        // 如果是0,默认当前时间
        if (toCalendar.get(Calendar.YEAR) <= 1970) {
            toCalendar.setTimeInMillis(System.currentTimeMillis());
        }

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取闪屏页时段问候语
     */
    public static String getDayState() {

        Date thisDate = new Date();

        if (thisDate.getHours() <= 11) {
            return "早上好";
        } else if (thisDate.getHours() <= 13) {
            return "中午好";

        } else if (thisDate.getHours() <= 18) {
            return "下午好";

        } else {
            return "晚上好";
        }
    }
}