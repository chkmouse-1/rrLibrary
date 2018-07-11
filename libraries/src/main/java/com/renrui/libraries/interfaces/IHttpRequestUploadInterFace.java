package com.renrui.libraries.interfaces;

public interface IHttpRequestUploadInterFace extends IHttpRequestInterFace {

    /**
     * 进度
     */
    void onProgress(int value);

    /**
     * 取消
     */
    void onCancel();
}
