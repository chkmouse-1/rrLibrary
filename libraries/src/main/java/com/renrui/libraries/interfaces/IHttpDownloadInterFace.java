package com.renrui.libraries.interfaces;

public interface IHttpDownloadInterFace {

    void onStart();

    void onResponse(byte[] fileByte);

    void onProgress(int process);

    void onErrorResponse(String content);

    void onFinish();
}
