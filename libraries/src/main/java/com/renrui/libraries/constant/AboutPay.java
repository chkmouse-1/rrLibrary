package com.renrui.libraries.constant;

/**
 * 支付相关
 * // TODO
 */
public class AboutPay {

    public static String WX_APP_ID = "wx5fb6e514fdfed8fa";

    public static void initAppID(String value) {
        WX_APP_ID = value;
    }

    // 授权相关
    public static final String WX_App_Auth_scope = "snsapi_userinfo";
//    public static final String WX_App_Auth_state = AppConfig.mySelfUrl;

    /**
     * 职位续期接口,余额不足
     */
    public static final String InterFace_Result_NoMoney = "15";

    /**
     * 付款成功
     */
    public static final String PayStat_PAID = "PAID";
    /**
     * 付款未成功
     */
    public static final String PayStat_TO_BE_PAID = "TO_BE_PAID";

    /**
     * 支付结果查询最大次数
     */
    public final static int payRequestMaxCounts = 3;
    /**
     * 支付请求间隔时间
     */
    public final static int payRequestDelayMillys = 100;
    /**
     * 支付请求当前查询次数
     */
    public static int payRequestCounts = 0;
}
