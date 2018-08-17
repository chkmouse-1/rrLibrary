package com.renrui.libraries.util;

import android.text.TextUtils;

import com.renrui.libraries.enumDef.Constellation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据相关的公共方法
 */
public class UtilityData {

    private static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22,
            23, 23, 23, 23, 22, 22};
    @Constellation.Def
    private static final String[] constellationArray = {Constellation.shuiping, Constellation.shuangyu, Constellation.baiyang,
            Constellation.jinniu, Constellation.shuangzi, Constellation.juxie, Constellation.shizi, Constellation.chunv, Constellation.tianping,
            Constellation.tianxie, Constellation.sheshou, Constellation.mojie};

    /**
     * 保留1位小数
     */
    public final static DecimalFormat Decimaldf1 = new DecimalFormat("0.0");

    /**
     * 保留2位小数
     */
    public final static DecimalFormat Decimaldf2 = new DecimalFormat("0.00");

    /**
     * 获取一级域名
     */
    public static String getLevelHost(String host) {
        String value = "";

        try {
            String regStr = "[0-9a-zA-Z]+((\\.com)|(\\.cn)|(\\.org)|(\\.net)|(\\.edu)|(\\.com.cn)|(\\.xyz)|(\\.xin)|(\\.club)|(\\.shop)|(\\.site)|(\\.wang)" +
                    "|(\\.top)|(\\.win)|(\\.online)|(\\.tech)|(\\.store)|(\\.bid)|(\\.cc)|(\\.ren)|(\\.lol)|(\\.pro)|(\\.red)|(\\.kim)|(\\.space)|(\\.link)|(\\.click)|(\\.news)|(\\.news)|(\\.ltd)|(\\.website)" +
                    "|(\\.biz)|(\\.help)|(\\.mom)|(\\.work)|(\\.date)|(\\.loan)|(\\.mobi)|(\\.live)|(\\.studio)|(\\.info)|(\\.pics)|(\\.photo)|(\\.trade)|(\\.vc)|(\\.party)|(\\.game)|(\\.rocks)|(\\.band)" +
                    "|(\\.gift)|(\\.wiki)|(\\.design)|(\\.software)|(\\.social)|(\\.lawyer)|(\\.engineer)|(\\.org)|(\\.net.cn)|(\\.org.cn)|(\\.gov.cn)|(\\.name)|(\\.tv)|(\\.me)|(\\.asia)|(\\.co)|(\\.press)|(\\.video)|(\\.market)" +
                    "|(\\.games)|(\\.science)|(\\.中国)|(\\.公司)|(\\.网络)|(\\.pub)" +
                    "|(\\.la)|(\\.auction)|(\\.email)|(\\.sex)|(\\.sexy)|(\\.one)|(\\.host)|(\\.rent)|(\\.fans)|(\\.cn.com)|(\\.life)|(\\.cool)|(\\.run)" +
                    "|(\\.gold)|(\\.rip)|(\\.ceo)|(\\.sale)|(\\.hk)|(\\.io)|(\\.gg)|(\\.tm)|(\\.com.hk)|(\\.gs)|(\\.us))";
            Pattern p = Pattern.compile(regStr);
            Matcher m = p.matcher(host);
            //获取一级域名
            while (m.find()) {
                value = m.group();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * 无论如何都展示一位小数
     */
    public static String getDecimaldf1All(double value) {
        String strValue = "";

        try {
            strValue = Decimaldf1.format(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValue;
    }

    /**
     * 无论如何都展示两位小数
     */
    public static String getDecimaldf2All(double value) {
        String strValue = "";

        try {
            strValue = Decimaldf2.format(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValue;
    }

    /**
     * 获取最多两位小数点的展示数
     * <p>
     * 如果两位小数都是0,则返回整数部分
     * 如果最后一位小数是0,则删掉最后一位小数
     */
    public static String getDecimaldf2(double value) {
        String strValue = "";

        try {
            strValue = String.valueOf(Decimaldf2.format(value));//.replace(".0","").replace(".00","");

            if (strValue.substring(strValue.length() - 2).equalsIgnoreCase("00")) {
                strValue = strValue.substring(0, strValue.length() - 3);
            } else if (strValue.substring(strValue.length() - 1).equalsIgnoreCase("0")) {
                strValue = strValue.substring(0, strValue.length() - 1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValue;
    }

    /**
     * 获取关键字在字符串的indexOf集合 (忽略大小写)
     *
     * @param str     字符串
     * @param hotword 关键字
     */
    public static List<Integer> getStrIndex(String str, String hotword) {

        List<Integer> lis = new ArrayList<>();

        // 忽略大小写
        final String tempLowerCaseStr = str.toLowerCase();
        final String tempLowerHotWord = hotword.toLowerCase();

        int indexOf = tempLowerCaseStr.indexOf(tempLowerHotWord);
        if (indexOf != -1) {
            lis.add(indexOf);
        }

        while (indexOf != -1) {

            indexOf = tempLowerCaseStr.indexOf(tempLowerHotWord, indexOf + 1);

            if (indexOf != -1) {
                lis.add(indexOf);
            }
        }

        return lis;
    }

    /**
     * 数字转汉字
     */
    public static String toChinese(int value) {

        final String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        final String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

        String result = "";

        try {
            int n = String.valueOf(value).length();
            for (int i = 0; i < n; i++) {

                int num = String.valueOf(value).charAt(i) - '0';

                if (i != n - 1 && num != 0) {
                    result += s1[num] + s2[n - 2 - i];
                } else {
                    result += s1[num];
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static String getStitching(List<String> list, String sti) {
        StringBuffer sb = new StringBuffer();

        try {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    sb.append(i == 0 ? list.get(i) : sti + list.get(i));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * @param phone 手机号
     * @return 将手机号的4-7位替换成星号
     */
    public static String getSecurityPhone(String phone) {
        String strPhone = "";

        if (TextUtils.isEmpty(phone) || phone.trim().length() != 11) {
            return strPhone;
        }

        try {
            strPhone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strPhone;
    }

    /**
     * @param phone 手机号
     * @return 将手机号的4-7位添加空格
     */
    public static String getPhoneFormat1(String phone) {
        String strPhone = "";

        if (TextUtils.isEmpty(phone) || phone.trim().length() != 11) {
            return strPhone;
        }

        try {
            strPhone = phone.substring(0, 3) + " " + phone.substring(3, 7) + " " + phone.substring(7, 11);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strPhone;
    }

//    /**
//     * 检查返回json字符串是否合法
//     *
//     * @param content content
//     */
//    public static boolean CheckResponseString(String content) {
//        return CheckResponseString(content, true);
//    }
//
//    /**
//     * 检查返回json字符串是否合法
//     * 默认检查resultCode，不等于0视为非法，且非法结构，默认弹错误提示框
//     */
//    public static boolean defaultCheckResponseString(String content) {
//        return CheckResponseString(content, true, true);
//    }
//
//    /**
//     * 检查返回json字符串是否合法
//     *
//     * @param content            content
//     * @param isShowErrorMessage 是否提示错误信息
//     */
//    public static boolean CheckResponseString(String content, boolean isShowErrorMessage) {
//
//        BaseResponseModel baseResponseModel;
//        try {
//            if (TextUtils.isEmpty(content)) {
//                if (isShowErrorMessage) {
//                    CustomToast.makeTextError(R.string.info_json_error);
//                }
//                return false;
//            }
//
//            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
//            if (baseResponseModel == null || baseResponseModel.result == null) {
//                if (isShowErrorMessage) {
//                    CustomToast.makeTextError(R.string.info_json_error);
//                }
//                return false;
//            }
//
//        } catch (Exception ex) {
//            if (isShowErrorMessage) {
//                CustomToast.makeTextError(R.string.info_json_error);
//            }
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 检查返回json字符串是否合法
//     *
//     * @param content            content
//     * @param isCheckResultCode  是否检查返回码
//     * @param isShowErrorMessage 是否提示错误信息
//     * @return
//     */
//    public static boolean CheckResponseString(String content, boolean isCheckResultCode, boolean isShowErrorMessage) {
//
//        BaseResponseModel baseResponseModel;
//        try {
//            if (TextUtils.isEmpty(content)) {
//                if (isShowErrorMessage) {
//                    CustomToast.makeTextError(R.string.info_json_error);
//                }
//                return false;
//            }
//
//            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
//            if (baseResponseModel == null || baseResponseModel.result == null) {
//                if (isShowErrorMessage) {
//                    CustomToast.makeTextError(R.string.info_json_error);
//                }
//                return false;
//            }
//
//            if (isCheckResultCode && baseResponseModel.result.code != 0) {
//                if (isShowErrorMessage) {
//                    if (TextUtils.isEmpty(baseResponseModel.result.msg)) {
//                        CustomToast.makeTextError(R.string.info_json_error);
//                    } else {
//                        CustomToast.makeTextError(baseResponseModel.result.msg);
//                    }
//                }
//                return false;
//            }
//
//        } catch (Exception ex) {
//            if (isShowErrorMessage) {
//                CustomToast.makeTextError(R.string.info_json_error);
//            }
//            return false;
//        }
//
//        return true;
//    }
//
//    public static BaseResponseModel getBaseResponseModel(String content) {
//        BaseResponseModel baseResponseModel = null;
//
//        try {
//            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        if (baseResponseModel == null) {
//            baseResponseModel = new BaseResponseModel();
//        }
//
//        return baseResponseModel;
//    }

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(long time) {

        String constellation;
        Calendar c = Calendar.getInstance();

        try {
            c.setTimeInMillis(time);

            constellation = date2Constellation(c);
        } catch (Exception e) {
            constellation = "";
        }

        return constellation;
    }

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(Calendar time) {
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArray[month];
        }
        // default to return 魔羯
        else {
            return constellationArray[11];
        }
    }
}