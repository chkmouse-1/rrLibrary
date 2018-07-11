package com.renrui.libraries.interfaces;

public interface IHttpRequestInterFace {

    void onStart();

    void onResponse(String content);

    void onErrorResponse(String content);

    void onFinish();
}
