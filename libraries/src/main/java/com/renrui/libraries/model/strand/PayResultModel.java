package com.renrui.libraries.model.strand;

import android.text.TextUtils;

import com.renrui.libraries.constant.AboutPay;
import com.renrui.libraries.model.baseObject.BaseDataProvider;
import com.renrui.libraries.util.UtilityTime;

/**
 * 支付结果查询
 */
public class PayResultModel extends BaseDataProvider {

    public String amount = "";
    public String date = "";
    public String payMode = "";
    public String oid = "";
    public String state = AboutPay.PayStat_TO_BE_PAID;

    public String getLocalDate() {
        String localDate;
        if (TextUtils.isEmpty(date)) {
            localDate = "未知时间";
        } else {
            localDate = UtilityTime.getSdfString(UtilityTime.sdf_19, date);
        }
        return localDate;
    }

    public boolean isPaySucceed() {
        return AboutPay.PayStat_PAID.equalsIgnoreCase(state);
    }

    public String getPayMode() {
        String mode;
        if ("ALIPAY".equalsIgnoreCase(payMode)) {
            mode = "支付宝";
        } else if ("WECHATPAY".equalsIgnoreCase(payMode)) {
            mode = "微信";
        } else {
            mode = "未知";
        }
        return mode;
    }
}
