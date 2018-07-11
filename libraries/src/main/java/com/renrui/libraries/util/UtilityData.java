package com.renrui.libraries.util;

import android.text.TextUtils;

import com.renrui.libraries.R;
import com.renrui.libraries.enumDef.Constellation;
import com.renrui.libraries.model.baseObject.BaseResponseModel;

import java.util.Calendar;

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
     * 检查返回json字符串是否合法
     *
     * @param content content
     */
    public static boolean CheckResponseString(String content) {
        return CheckResponseString(content, true);
    }

    /**
     * 检查返回json字符串是否合法
     * 默认检查resultCode，不等于0视为非法，且非法结构，默认弹错误提示框
     */
    public static boolean tfytCheckResponseString(String content) {
        return CheckResponseString(content, true, true);
    }

    /**
     * 检查返回json字符串是否合法
     *
     * @param content            content
     * @param isShowErrorMessage 是否提示错误信息
     */
    public static boolean CheckResponseString(String content, boolean isShowErrorMessage) {

        BaseResponseModel baseResponseModel;
        try {
            if (TextUtils.isEmpty(content)) {
                if (isShowErrorMessage) {
                    CustomToast.makeTextError(R.string.info_json_error);
                }
                return false;
            }

            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
            if (baseResponseModel == null || baseResponseModel.result == null) {
                if (isShowErrorMessage) {
                    CustomToast.makeTextError(R.string.info_json_error);
                }
                return false;
            }

        } catch (Exception ex) {
            if (isShowErrorMessage) {
                CustomToast.makeTextError(R.string.info_json_error);
            }
            return false;
        }

        return true;
    }

    /**
     * 检查返回json字符串是否合法
     *
     * @param content            content
     * @param isCheckResultCode  是否检查返回码
     * @param isShowErrorMessage 是否提示错误信息
     * @return
     */
    public static boolean CheckResponseString(String content, boolean isCheckResultCode, boolean isShowErrorMessage) {

        BaseResponseModel baseResponseModel;
        try {
            if (TextUtils.isEmpty(content)) {
                if (isShowErrorMessage) {
                    CustomToast.makeTextError(R.string.info_json_error);
                }
                return false;
            }

            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
            if (baseResponseModel == null || baseResponseModel.result == null) {
                if (isShowErrorMessage) {
                    CustomToast.makeTextError(R.string.info_json_error);
                }
                return false;
            }

            if (isCheckResultCode && !baseResponseModel.result.code.equals("0")) {
                if (isShowErrorMessage) {
                    if (TextUtils.isEmpty(baseResponseModel.result.msg)) {
                        CustomToast.makeTextError(R.string.info_json_error);
                    } else {
                        CustomToast.makeTextError(baseResponseModel.result.msg);
                    }
                }
                return false;
            }

        } catch (Exception ex) {
            if (isShowErrorMessage) {
                CustomToast.makeTextError(R.string.info_json_error);
            }
            return false;
        }

        return true;
    }

    public static BaseResponseModel getBaseResponseModel(String content) {
        BaseResponseModel baseResponseModel = null;

        try {
            baseResponseModel = mHttpClient.GetGsonInstance().fromJson(content, BaseResponseModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (baseResponseModel == null) {
            baseResponseModel = new BaseResponseModel();
        }

        return baseResponseModel;
    }

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