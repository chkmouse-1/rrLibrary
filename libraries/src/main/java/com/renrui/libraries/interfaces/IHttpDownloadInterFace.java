package com.renrui.libraries.interfaces;

import cz.msebera.android.httpclient.Header;

public interface IHttpDownloadInterFace {

    void onStart();

    void onResponse(Header[] headers, byte[] fileByte);

    void onProgress(int process);

    void onErrorResponse(String content);

    void onFinish();
}
