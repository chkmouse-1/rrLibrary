package com.renrui.libraries.model.baseObject;

import com.renrui.libraries.enumDef.HttpRequestType;

import java.io.File;

/**
 * 网络接口基类
 */
public abstract class BaseHttpModel {

    /**
     * 请求类型,默认get
     */
    @HttpRequestType.Def
    protected int requestType = HttpRequestType.Get;

    public int getRequestType() {
        return this.requestType;
    }

    /**
     * 是否PostJson
     */
    private boolean isPostJson = false;

    /**
     * @param value 是否PostJson
     */
    public void setIsPostJson(boolean value) {
        this.requestType = HttpRequestType.Post;
        this.isPostJson = value;
    }

    public boolean getIsPostJson() {
        return this.isPostJson;
    }

    /**
     * postJsonText
     */
    private String postJsonText = "";

    /**
     * @param value 设置PostJsonText
     */
    public void setPostJsonText(String value) {
        this.postJsonText = value;
    }

    public String getPostJsonText() {
        return this.postJsonText;
    }

    /**
     * 获取网络地址
     */
    public abstract String getUrl();
}
