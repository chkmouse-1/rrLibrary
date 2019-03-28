package com.renrui.libraries.util;

import android.text.TextUtils;

import com.renrui.libraries.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilityTime {

    /**
     * yyyy-MM-dd HH:mm
     */
    public final static SimpleDateFormat sdf_1 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf1));

    /**
     * yyyy-MM-dd
     */
    public final static SimpleDateFormat sdf_2 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf2));

    /**
     * yyyy-MM-dd
     */
    public final static SimpleDateFormat sdf_3 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf3));

    /**
     * HH:mm
     */
    public final static SimpleDateFormat sdf_4 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf4));

    /**
     * MM-dd HH:mm
     */
    public final static SimpleDateFormat sdf_5 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf5));

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
    public final static SimpleDateFormat sdf_9 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf9));

    /**
     * yyyy
     */
    public final static SimpleDateFormat sdf_10 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf10));

    /**
     * MM
     */
    public final static SimpleDateFormat sdf_11 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf11));

    /**
     * MM/dd
     */
    public final static SimpleDateFormat sdf_12 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf12));

    /**
     * yyyy/MM/dd
     */
    public final static SimpleDateFormat sdf_13 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf13));

    /**
     * yyyy年MM月dd日 HH:mm
     */
    public final static SimpleDateFormat sdf_14 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf14));

    /**
     * yyyy.MM.dd
     */
    public final static SimpleDateFormat sdf_15 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf15));

    /**
     * MM月dd日 HH:mm
     */
    public final static SimpleDateFormat sdf_16 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf16));

    /**
     * M.dd HH:mm
     */
    public final static SimpleDateFormat sdf_17 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf17));

    /**
     * M.dd HH:mm
     */
    public final static SimpleDateFormat sdf_18 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf18));

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static SimpleDateFormat sdf_19 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf19));

    /**
     * yyyy-MM
     */
    public final static SimpleDateFormat sdf_20 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf20));

    /**
     * MM-dd
     */
    public final static SimpleDateFormat sdf_21 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf21));

    /**
     * MM-dd
     */
    public final static SimpleDateFormat sdf_22 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf22));

    /**
     * HH:mm:ss
     */
    public final static SimpleDateFormat sdf_23 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf23));

    /**
     * HH时mm分ss秒
     */
    public final static SimpleDateFormat sdf_24 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf24));

    /**
     * MM.dd
     */
    public final static SimpleDateFormat sdf_25 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf25));

    /**
     * yyyy-MM-dd HH:mm
     */
    public final static SimpleDateFormat sdf_26 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf26));
    /**
     * yyyy.MM.dd HH:mm
     */
    public final static SimpleDateFormat sdf_27 = new SimpleDateFormat(LibrariesCons.getContext().getString(R.string.UtilityTime_sdf27));


    private final static String Yesterday = LibrariesCons.getContext().getString(R.string.UtilityTime_Yesterday);
    private final static String Today = LibrariesCons.getContext().getString(R.string.UtilityTime_Today);
    private final static String Tomorrow = LibrariesCons.getContext().getString(R.string.UtilityTime_Tomorrow);
    private final static String DayAfterTomorrow = LibrariesCons.getContext().getString(R.string.UtilityTime_Day_After_Tomorrow);

    /**
     * 1秒钟毫秒数
     */
    public final static long lSecondTimes = 1000;

    /**
     * 1分钟毫秒数
     */
    public final static long lMinuteTimes = lSecondTimes * 60;

    /**
     * 1小时毫秒数
     */
    public final static long lHourTimes = lMinuteTimes * 60;

    /**
     * 1天毫秒数
     */
    public final static long lDayTimes = lHourTimes * 24;

    /**
     * 1周毫秒数
     */
    public final static long lWeekTimes = lDayTimes * 7;

    private static Date tempDate;
    private static Date tempThisDate = new Date();
    private static Calendar tempCal = Calendar.getInstance();

    public static String getWeek(int day) {

        int stringID;

        if (day == Calendar.MONDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Monday;
        } else if (day == Calendar.TUESDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Tuesday;
        } else if (day == Calendar.WEDNESDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Wednesday;
        } else if (day == Calendar.THURSDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Thursday;
        } else if (day == Calendar.FRIDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Friday;
        } else if (day == Calendar.SATURDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Saturday;
        } else if (day == Calendar.SUNDAY) {
            stringID = R.string.UtilityTime_Week_Simple_Sunday;
        } else {
            stringID = R.string.Utility_unknown;
        }

        return LibrariesCons.getContext().getString(stringID);
    }

    public static String getWeekAllText(int day) {
        int stringID;

        if (day == Calendar.MONDAY) {
            stringID = R.string.UtilityTime_Week_Monday;
        } else if (day == Calendar.TUESDAY) {
            stringID = R.string.UtilityTime_Week_Tuesday;
        } else if (day == Calendar.WEDNESDAY) {
            stringID = R.string.UtilityTime_Week_Wednesday;
        } else if (day == Calendar.THURSDAY) {
            stringID = R.string.UtilityTime_Week_Thursday;
        } else if (day == Calendar.FRIDAY) {
            stringID = R.string.UtilityTime_Week_Friday;
        } else if (day == Calendar.SATURDAY) {
            stringID = R.string.UtilityTime_Week_Saturday;
        } else if (day == Calendar.SUNDAY) {
            stringID = R.string.UtilityTime_Week_Sunday;
        } else {
            stringID = R.string.Utility_unknown;
        }

        return LibrariesCons.getContext().getString(stringID);
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
                    return Today + UtilityTime.sdf_4.format(Long.parseLong(lTime));
                }

                // 昨天：昨天 HH:mm
                UtilityTime.tempCal.setTime(UtilityTime.tempDate);
                UtilityTime.tempCal.add(Calendar.DAY_OF_YEAR, 1);
                if (UtilityTime.tempThisDate.getYear() == UtilityTime.tempCal.getTime().getYear() && UtilityTime.tempThisDate.getMonth() == UtilityTime.tempCal.getTime().getMonth() && UtilityTime.tempThisDate.getDate() == UtilityTime.tempCal.getTime().getDate()) {
                    return Yesterday + UtilityTime.sdf_4.format(Long.parseLong(lTime));
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
                return Today + UtilityTime.sdf_4.format(lTime);
            }

            // 昨天：昨天 HH:mm
            UtilityTime.tempCal.setTime(UtilityTime.tempDate);
            UtilityTime.tempCal.add(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempThisDate.getYear() == UtilityTime.tempCal.getTime().getYear() && UtilityTime.tempThisDate.getMonth() == UtilityTime.tempCal.getTime().getMonth() && UtilityTime.tempThisDate.getDate() == UtilityTime.tempCal.getTime().getDate()) {
                return Yesterday + UtilityTime.sdf_4.format(lTime);
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
                return Today;
            }

            // 明天
            calThis.roll(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return Tomorrow;
            }

            // 后天
            calThis.roll(Calendar.DAY_OF_YEAR, 1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return DayAfterTomorrow;
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
                return Today;
            }

            // 昨天
            calThis.roll(Calendar.DAY_OF_YEAR, -1);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return Yesterday;
            }

            // 明天
            calThis.roll(Calendar.DAY_OF_YEAR, 2);
            if (UtilityTime.tempCal.getTime().getYear() == calThis.getTime().getYear() && UtilityTime.tempCal.getTime().getMonth() == calThis.getTime().getMonth() && UtilityTime.tempCal.getTime().getDate() == calThis.getTime().getDate()) {
                return Tomorrow;
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
                return Today + UtilityTime.sdf_4.format(UtilityTime.tempDate);
            }

            Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(UtilityTime.tempDate);
            calendar.roll(Calendar.DAY_OF_YEAR, 1);
            // 当天： 昨天 MM-dd
            if (calendar.getTime().getYear() == thisDate.getYear() && calendar.getTime().getMonth() == thisDate.getMonth() && calendar.getTime().getDate() == thisDate.getDate()) {
                return Yesterday + UtilityTime.sdf_4.format(UtilityTime.tempDate);
            }

            return UtilityTime.sdf_5.format(UtilityTime.tempDate);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 计算当前传递过来的时间和当前时间的差距
     */
    public static String calculTimeDiff(long time) {
        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - time;
        if (timeDiff <= UtilityTime.lMinuteTimes) {
            return "刚刚";
        } else if (timeDiff <= UtilityTime.lHourTimes) {
            int min = (int) (timeDiff / UtilityTime.lMinuteTimes);
            return min + "分钟前";
        } else if (timeDiff <= 24 * UtilityTime.lHourTimes) {
            int hour = (int) (timeDiff / UtilityTime.lHourTimes);
            return hour + "小时前";
        } else if (timeDiff <= 24 * 30 * UtilityTime.lHourTimes) {
            int day = (int) (timeDiff / (UtilityTime.lHourTimes * 24));
            return day + "天前";
        } else if (timeDiff < 24 * 365 * UtilityTime.lHourTimes) {
            int mouth = (int) (timeDiff / (UtilityTime.lHourTimes * 24 * 30));
            return mouth + "月前";
        } else {
            SimpleDateFormat format = sdf_2;
            return format.format(new Date(time));
        }
    }

    /**
     * 获取和当前时间间隔差的展示text
     */
    public static String getActiveTimeText(long value) {
        String text = "";

        try {
            long intervalTime = System.currentTimeMillis() - value;

            if (intervalTime <= lMinuteTimes * 5) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_just);
            } else if (intervalTime <= (lMinuteTimes * 10)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_10Minute);
            } else if (intervalTime <= (lMinuteTimes * 20)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_20Minute);
            } else if (intervalTime <= (lMinuteTimes * 30)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_30Minute);
            } else if (intervalTime <= (lHourTimes * 1)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_1Hour);
            } else if (intervalTime <= (lHourTimes * 2)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_2Hour);
            } else if (intervalTime <= (lHourTimes * 3)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_3Hour);
            } else if (intervalTime <= (lHourTimes * 4)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_4Hour);
            } else if (intervalTime <= (lHourTimes * 5)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_5Hour);
            } else if (intervalTime <= (lHourTimes * 6)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_6Hour);
            } else if (intervalTime <= (lHourTimes * 7)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_7Hour);
            } else if (intervalTime <= (lHourTimes * 8)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_8Hour);
            } else if (intervalTime <= (lHourTimes * 9)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_9Hour);
            } else if (intervalTime <= (lHourTimes * 10)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_10Hour);
            } else if (intervalTime <= (lHourTimes * 11)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_11Hour);
            } else if (intervalTime <= (lHourTimes * 12)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_12Hour);
            } else if (intervalTime <= lDayTimes) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_one_day);
            } else if (intervalTime <= (lDayTimes * 2)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_two_day);
            } else if (intervalTime <= (lDayTimes * 3)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_three_day);
            } else if (intervalTime <= (lDayTimes * 7)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_one_week);
            } else if (intervalTime <= (lDayTimes * 14)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_two_week);
            } else if (intervalTime <= (lDayTimes * 30)) {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_one_month);
            } else {
                text = LibrariesCons.getContext().getString(R.string.UtilityTime_one_month_more);
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
            return LibrariesCons.getContext().getString(R.string.UtilityTime_good_morning);

        } else if (thisDate.getHours() <= 13) {
            return LibrariesCons.getContext().getString(R.string.UtilityTime_good_noon);

        } else if (thisDate.getHours() <= 18) {
            return LibrariesCons.getContext().getString(R.string.UtilityTime_good_afternoon);

        } else {
            return LibrariesCons.getContext().getString(R.string.UtilityTime_good_evening);
        }
    }
}