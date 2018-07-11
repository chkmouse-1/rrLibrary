package com.renrui.libraries.interfaces;

public interface IHttpDownloadInterFace {

    void onStart();

    void onResponse(byte[] fileByte);

    void onErrorResponse(String content);

    void onFinish();
}
