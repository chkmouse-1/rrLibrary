package com.renrui.libraries.model.baseObject;

public class ResultResponseModel extends BaseDataProvider {

    /**
     * 返回码
     * 0     为正常
     * 14    账号被封
     * 其他  为错误
     */
    public int code = 0;

    /**
     * 错误信息描述
     */
    public String msg = "";

    /**
     * 增加的积分
     */
    public int score = 0;
}