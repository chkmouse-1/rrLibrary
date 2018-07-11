package com.renrui.libraries.model.strand;

import com.renrui.libraries.model.baseObject.BaseDataProvider;

/**
 * 发送验证码
 */
public class PhoneLoginModel extends BaseDataProvider {

    /**
     * 手机号
     */
    public String mobile = "";

    /**
     * 验证码
     */
    public String vercode = "";

    /**
     * 密码
     */
    public String passwd = "";

    /**
     * 原密码
     */
    public String oldPasswd = "";

    public String verkey = "";
}
