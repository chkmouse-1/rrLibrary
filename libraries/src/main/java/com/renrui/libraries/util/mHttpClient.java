package com.renrui.libraries.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.renrui.libraries.BuildConfig;
import com.renrui.libraries.R;
import com.renrui.libraries.constant.AboutPay;
import com.renrui.libraries.enumDef.HttpRequestType;
import com.renrui.libraries.interfaces.IHttpDownloadInterFace;
import com.renrui.libraries.interfaces.IHttpRequestCancelInterFace;
import com.renrui.libraries.interfaces.IHttpRequestInterFace;
import com.renrui.libraries.interfaces.IHttpRequestUploadInterFace;
import com.renrui.libraries.model.baseObject.BaseHttpModel;
import com.renrui.libraries.model.httpinterface.PayResultResponseModel;

import org.getopt.util.hash.FNV1a32;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

/**
 * 网络请求
 */
public class mHttpClient {

    public static String UserAgent_Key = " tfZAEEECgYEA/oXQfRXIocuFTdjY6ZFwhCIg8KNTUPsuhQpZrbTDWfkVJH8VJIUS";
    public static final String User_Agent_Format_befor = "%1$s %2$s %3$s %4$s %5$s";
    public static final String User_Agent_Format = "/%1$s (%2$s; %3$s; %4$s; %5$s; cs:%6$s; ch:%7$s)";

    private static final String HttpContentType = "application/json;charset=UTF-8";

    public static final String xguid = "xguid";
    public static final String xgtok = "xgtok";

    private static Gson gson = null;
    private static ByteArrayEntity postArrEntity = null;

    public static void setUserAgentKey(String value) {
        UserAgent_Key = value;
    }

    private static String channelName = "";

    public static Gson GetGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }

        return gson;
    }

    public static Gson GetGsonWithoutExposeAnnotation() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    private static AsyncHttpClient mAsyncHttpClient = null;

    public static AsyncHttpClient getAsyncHttpClient() {

        if (mAsyncHttpClient == null) {
            mAsyncHttpClient = new AsyncHttpClient();
//            mAsyncHttpClient = new AsyncHttpClient(true, 80, 443);
            initAsyn();
        }

        return mAsyncHttpClient;
    }

    /**
     * 重新初始化网络请求对象
     */
    public static void reInitAsyn() {
        try {
            mAsyncHttpClient = null;
            getAsyncHttpClient();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void initAsyn() {
        try {
            // 最大并发
            mAsyncHttpClient.setMaxConnections(LibrariesCons.httpMaxConnections);

            // 超时时间
            mAsyncHttpClient.setMaxRetriesAndTimeout(LibrariesCons.httpRetryCounts, LibrariesCons.httpTimeout);

            // 设置超时
            mAsyncHttpClient.setTimeout(LibrariesCons.httpTimeout);

            mAsyncHttpClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());

            mAsyncHttpClient.setURLEncodingEnabled(false);

//            // 设置UserAgent
//            mAsyncHttpClient.setUserAgent(UserAgentUtility.getUserAgent(true));

            // 设置Cookie
//            setHttpCookie();

//            // 优先设置配置文件的代理IP，其次设置当前手机的IP
//            if (UtilitySharedPreferences.readBoolean(UtilitySharedPreferences.boolean_isuse_proxy)) {
//                String proxyIP = UtilitySharedPreferences.readString(UtilitySharedPreferences.string_proxy_ip);
//                if (!TextUtils.isEmpty(proxyIP) && UtilityNetWork.isIP(proxyIP)) {
//                    mAsyncHttpClient.setProxy(proxyIP, 8888);
//                }
//            } else if (!TextUtils.isEmpty(BuildConfig.PROXY) && UtilityNetWork.isIP(BuildConfig.PROXY)) {
//                mAsyncHttpClient.setProxy(BuildConfig.PROXY, 8888);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setChannelName(String channelName) {
        mHttpClient.channelName = channelName;
    }

    /**
     * 获取UserAgent
     *
     * @param uaStart
     */
    public static String getUserAgent(String uaStart) {
        String userAgent = "";
        try {
            PackageManager info = LibrariesCons.getContext().getPackageManager();
            PackageInfo packageInfo = info.getPackageInfo(LibrariesCons.getContext().getPackageName(), 0);

            final String uuid = LibUtility.getUUID();
            final String phoneInfo = String.format(User_Agent_Format_befor, packageInfo.versionName, android.os.Build.MODEL, "Android " + android.os.Build.VERSION.RELEASE, Locale.getDefault().toString(), uuid);
            FNV1a32 fnv1a32 = new FNV1a32();
            fnv1a32.init(phoneInfo + UserAgent_Key);
            final long hash = fnv1a32.getHash();
            final String cs = Long.toHexString(hash);

            userAgent = uaStart + String.format(User_Agent_Format, packageInfo.versionName, android.os.Build.MODEL, "Android " + android.os.Build.VERSION.RELEASE, Locale.getDefault().toString(), uuid, cs, channelName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userAgent;
    }

    public static void setUserAgent(String uaStart) {
        try {
            // 设置UserAgent
            getAsyncHttpClient().setUserAgent(getUserAgent(uaStart));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置cookie
     */
    public static void setHttpCookie(String domain, HashMap<String, String> hpCookies) {

        if (hpCookies == null || hpCookies.isEmpty()) {
            return;
        }

        PersistentCookieStore myCookieStore = new PersistentCookieStore(LibrariesCons.getContext());

        try {
            BasicClientCookie cookie;

            for (Map.Entry<String, String> entry : hpCookies.entrySet()) {
                cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                cookie.setDomain(domain);
                myCookieStore.addCookie(cookie);
            }

            if (!myCookieStore.getCookies().isEmpty()) {
                getAsyncHttpClient().setCookieStore(myCookieStore);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getCookie() {

        String strCookie = "";

        try {
            PersistentCookieStore myCookies = (PersistentCookieStore) mAsyncHttpClient.getHttpContext().getAttribute(ClientContext.COOKIE_STORE);
            for (int i = 0; i < myCookies.getCookies().size(); i++) {
                strCookie += String.format(";%s=%s", myCookies.getCookies().get(i).getName(), myCookies.getCookies().get(i).getValue());
            }

            if (!TextUtils.isEmpty(strCookie)) {
                strCookie = strCookie.substring(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strCookie;
    }

    public static void clearCookie() {
        try {
            PersistentCookieStore myCookieStore = new PersistentCookieStore(LibrariesCons.getContext());
            getAsyncHttpClient().setCookieStore(myCookieStore);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void cancelRequests(Context mContext) {
        try {
            getAsyncHttpClient().cancelRequests(mContext, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param url                    接口地址
     * @param mIHttpRequestInterFace 回调
     */
    public static void HttpGet(Context mContext, String url, final IHttpRequestInterFace mIHttpRequestInterFace) {
        HttpGet(mContext, url, 0, mIHttpRequestInterFace);
    }

    /**
     * @param model
     * @param mIHttpRequestInterFace 回调
     */
    public static void Request(final Context mContext, BaseHttpModel model, final IHttpRequestInterFace mIHttpRequestInterFace) {
        try {
            switch (model.getRequestType()) {
                // get
                case HttpRequestType.Get:
                    HttpGet(mContext, model.getUrl(), LibUtility.getUrlParams(model), mIHttpRequestInterFace);
                    break;

                // post
                case HttpRequestType.Post:
                    // post text
                    if (!model.getIsPostJson()) {
                        HttpPost(mContext, model.getUrl(), LibUtility.getUrlParams(model), mIHttpRequestInterFace);
                    }
                    // post Text
                    else if (model.getIsPostJson() && !TextUtils.isEmpty(model.getPostJsonText())) {
                        HttpPost(mContext, model.getUrl(), model.getPostJsonText(), mIHttpRequestInterFace);
                    }
                    break;

                default:
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param url                    接口地址
     * @param para                   参数
     * @param mIHttpRequestInterFace 回调
     */
    public static void HttpGet(final Context mContext, String url, RequestParams para, final IHttpRequestInterFace mIHttpRequestInterFace) {
        try {
            if (null == mContext || TextUtils.isEmpty(url)) {
                return;
            }

            // 超时时间
            getAsyncHttpClient().setTimeout(LibrariesCons.httpTimeout);

            if (!UtilityNetWork.isNetworkAvailable()) {
                if (mIHttpRequestInterFace != null) {
                    mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                    mIHttpRequestInterFace.onFinish();
                }
                return;
            }

            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onStart();
            }

            getAsyncHttpClient().get(url, para, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    if (mIHttpRequestInterFace != null) {

                        if (responseBody != null) {
                            if (statusCode == 200) {
                                mIHttpRequestInterFace.onResponse(new String(responseBody));
                            } else {
                                mIHttpRequestInterFace.onErrorResponse(new String(responseBody));
                            }
                        }

                        mIHttpRequestInterFace.onFinish();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    if (mIHttpRequestInterFace != null) {
                        if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                            mIHttpRequestInterFace.onErrorResponse(error.getMessage());
                        }
                        mIHttpRequestInterFace.onFinish();
                    }
                }

                @Override
                public void onCancel() {
                    if (mIHttpRequestInterFace != null) {
                        // 需要OnCancel
                        if (mIHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                            ((IHttpRequestCancelInterFace) mIHttpRequestInterFace).onCancel();
                            mIHttpRequestInterFace.onFinish();
                        }
                        // 不需要OnCancel
                        else if (mIHttpRequestInterFace instanceof IHttpRequestInterFace) {
                            mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                            mIHttpRequestInterFace.onFinish();
                        }
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param url                    接口地址
     * @param retryCounts            重试次数
     * @param mIHttpRequestInterFace 回调
     */
    public static void HttpGet(final Context mContext, String url, int retryCounts, final IHttpRequestInterFace mIHttpRequestInterFace) {
        try {
            if (null == mContext || TextUtils.isEmpty(url)) {
                return;
            }

            // 超时时间
            getAsyncHttpClient().setTimeout(LibrariesCons.httpTimeout);

            if (!UtilityNetWork.isNetworkAvailable()) {
                if (mIHttpRequestInterFace != null) {
                    mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                    mIHttpRequestInterFace.onFinish();
                }
                return;
            }

            getAsyncHttpClient().setMaxRetriesAndTimeout(retryCounts, LibrariesCons.httpTimeout);

            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onStart();
            }

            getAsyncHttpClient().get(mContext, url, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    if (mIHttpRequestInterFace != null) {

                        if (responseBody != null) {
                            if (statusCode == 200) {
                                mIHttpRequestInterFace.onResponse(new String(responseBody));
                            } else {
                                mIHttpRequestInterFace.onErrorResponse(new String(responseBody));
                            }
                        }

                        mIHttpRequestInterFace.onFinish();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    if (mIHttpRequestInterFace != null) {
                        if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                            mIHttpRequestInterFace.onErrorResponse(error.getMessage());
                        }
                        mIHttpRequestInterFace.onFinish();
                    }
                }

                @Override
                public void onCancel() {
                    if (mIHttpRequestInterFace != null) {
                        // 需要OnCancel
                        if (mIHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                            ((IHttpRequestCancelInterFace) mIHttpRequestInterFace).onCancel();
                            mIHttpRequestInterFace.onFinish();
                        }
                        // 不需要OnCancel
                        else if (mIHttpRequestInterFace instanceof IHttpRequestInterFace) {
                            mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                            mIHttpRequestInterFace.onFinish();
                        }
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void HttpGetPay(final Activity mContext, final String url, final IHttpRequestInterFace mIHttpRequestInterFace) {
        AboutPay.payRequestCounts = 0;
        HttpGetPayRecursion(mContext, url, mIHttpRequestInterFace);
    }

    /**
     * 支付结果查询,如果未成功自动延迟查询
     *
     * @param url                    接口地址
     * @param mIHttpRequestInterFace 回调
     */
    private static void HttpGetPayRecursion(final Activity mContext, final String url, final IHttpRequestInterFace mIHttpRequestInterFace) {
        try {
            if (null == mContext || TextUtils.isEmpty(url)) {
                return;
            }

            // 超时时间
            getAsyncHttpClient().setTimeout(LibrariesCons.httpTimeout);

            if (!UtilityNetWork.isNetworkAvailable()) {
                if (mIHttpRequestInterFace != null) {
                    mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                    mIHttpRequestInterFace.onFinish();
                }
                return;
            }

            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onStart();
            }

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getAsyncHttpClient().get(mContext, url, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            if (mIHttpRequestInterFace == null) {
                                return;
                            }

                            if (statusCode != 200) {
                                mIHttpRequestInterFace.onErrorResponse(new String(responseBody));
                                return;
                            }

                            if (UtilityData.CheckResponseString(new String(responseBody), true)) {
                                PayResultResponseModel response = null;
                                try {
                                    response = mHttpClient.GetGsonInstance().fromJson(new String(responseBody), PayResultResponseModel.class);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if (response != null && response.data != null && !TextUtils.isEmpty(response.data.state)) {
                                    // 还未支付成功
                                    if (!response.data.isPaySucceed() && AboutPay.payRequestCounts < AboutPay.payRequestMaxCounts) {
                                        AboutPay.payRequestCounts++;
                                        HttpGetPayRecursion(mContext, url, mIHttpRequestInterFace);
                                    }
                                    // 成功
                                    else {
                                        mIHttpRequestInterFace.onResponse(new String(responseBody));
                                        mIHttpRequestInterFace.onFinish();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancel() {
                            // 需要OnCancel
                            if (mIHttpRequestInterFace != null) {
                                if (mIHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                                    ((IHttpRequestCancelInterFace) mIHttpRequestInterFace).onCancel();
                                    mIHttpRequestInterFace.onFinish();
                                }
                                // 不需要OnCancel
                                else if (mIHttpRequestInterFace instanceof IHttpRequestInterFace) {
                                    mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                                    mIHttpRequestInterFace.onFinish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            if (mIHttpRequestInterFace != null) {
                                if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                                    mIHttpRequestInterFace.onErrorResponse(error.getMessage());
                                }
                                mIHttpRequestInterFace.onFinish();
                            }
                        }
                    });
                }
            }, (AboutPay.payRequestDelayMillys) * (AboutPay.payRequestCounts + 1));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void HttpPost(Context mContext, String url, String json, final IHttpRequestInterFace mIHttpRequestInterFace) {
        if (null == mContext || TextUtils.isEmpty(url)) {
            return;
        }

        // 超时时间
        getAsyncHttpClient().setTimeout(LibrariesCons.httpTimeout);

        if (!UtilityNetWork.isNetworkAvailable()) {
            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                mIHttpRequestInterFace.onFinish();
            }
            return;
        }

        if (mIHttpRequestInterFace != null) {
            mIHttpRequestInterFace.onStart();
        }

        try {
            postArrEntity = new ByteArrayEntity(json.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
            postArrEntity = null;
        }

        if (postArrEntity == null) {
            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onErrorResponse("请求数据格式错误！");
                mIHttpRequestInterFace.onFinish();
            }
            return;
        }

        getAsyncHttpClient().post(mContext, url, postArrEntity, HttpContentType, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (mIHttpRequestInterFace != null) {
                    mIHttpRequestInterFace.onResponse(new String(responseBody));
                    mIHttpRequestInterFace.onFinish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (mIHttpRequestInterFace != null) {
                    if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                        mIHttpRequestInterFace.onErrorResponse(error.getMessage());
                    }
                    mIHttpRequestInterFace.onFinish();
                }
            }

            @Override
            public void onCancel() {
                if (mIHttpRequestInterFace != null) {

                    // 需要OnCancel
                    if (mIHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                        ((IHttpRequestCancelInterFace) mIHttpRequestInterFace).onCancel();
                        mIHttpRequestInterFace.onFinish();
                    }
                    // 不需要OnCancel
                    else if (mIHttpRequestInterFace instanceof IHttpRequestInterFace) {
                        mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                        mIHttpRequestInterFace.onFinish();
                    }
                }
            }
        });
    }

    public static RequestHandle HttpPost(Context mContext, String url, RequestParams para, final IHttpRequestInterFace mIHttpRequestInterFace) {
        return HttpPost(mContext, url, para, LibrariesCons.uploadFilehttpTimeout, mIHttpRequestInterFace);
    }

    /**
     * @param para    RequestParams
     * @param timeOut 超时时间
     */
    public static RequestHandle HttpPost(Context mContext, String url, RequestParams para, int timeOut, final IHttpRequestInterFace mIHttpRequestInterFace) {

        RequestHandle requestHandle;

        if (null == mContext || TextUtils.isEmpty(url)) {
            return null;
        }

        // 超时时间
        getAsyncHttpClient().setTimeout(timeOut);

        if (!UtilityNetWork.isNetworkAvailable()) {
            if (mIHttpRequestInterFace != null) {
                mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                mIHttpRequestInterFace.onFinish();
            }
            return null;
        }

        if (mIHttpRequestInterFace != null) {
            mIHttpRequestInterFace.onStart();
        }

        requestHandle = getAsyncHttpClient().post(mContext, url, para, new AsyncHttpResponseHandler() {

            // 上传进度
            int progress = 0;

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (mIHttpRequestInterFace != null) {
                    mIHttpRequestInterFace.onResponse(new String(responseBody));
                    mIHttpRequestInterFace.onFinish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (mIHttpRequestInterFace != null) {
                    if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                        mIHttpRequestInterFace.onErrorResponse(error.getMessage());
                    }
                    mIHttpRequestInterFace.onFinish();
                }
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                if (mIHttpRequestInterFace != null && mIHttpRequestInterFace instanceof IHttpRequestUploadInterFace) {
                    try {
                        if (totalSize > 10) {
                            progress = (int) ((Double.parseDouble(bytesWritten + "") / Double.parseDouble(totalSize + "")) * 100);
                            ((IHttpRequestUploadInterFace) mIHttpRequestInterFace).onProgress(progress);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancel() {
                if (mIHttpRequestInterFace != null) {
                    // 需要OnCancel
                    if (mIHttpRequestInterFace instanceof IHttpRequestUploadInterFace) {
                        ((IHttpRequestUploadInterFace) mIHttpRequestInterFace).onCancel();
                        mIHttpRequestInterFace.onFinish();
                    }
                    // 需要OnCancel
                    else if (mIHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                        ((IHttpRequestCancelInterFace) mIHttpRequestInterFace).onCancel();
                        mIHttpRequestInterFace.onFinish();
                    }
                    // 不需要OnCancel
                    else if (mIHttpRequestInterFace instanceof IHttpRequestInterFace) {
                        mIHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                        mIHttpRequestInterFace.onFinish();
                    }
                }
            }
        });

        return requestHandle;
    }

    /**
     * 下载
     */
    public static void downloadImage(Context mContext, String uri, final IHttpDownloadInterFace mHttpRequestInterFace) {

        if (null == mContext || TextUtils.isEmpty(uri)) {
            return;
        }

        if (!UtilityNetWork.isNetworkAvailable()) {
            if (mHttpRequestInterFace != null) {
                mHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_network));
                mHttpRequestInterFace.onFinish();
            }
            return;
        }

        if (mHttpRequestInterFace != null) {
            mHttpRequestInterFace.onStart();
        }

        getAsyncHttpClient().get(mContext, uri, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                try {
                    if (statusCode == 200 && binaryData != null && binaryData.length > 0) {
                        if (mHttpRequestInterFace != null) {
                            mHttpRequestInterFace.onResponse(binaryData);
                            mHttpRequestInterFace.onFinish();
                        }
                    } else {
                        if (mHttpRequestInterFace != null) {
                            mHttpRequestInterFace.onErrorResponse("下载失败！");
                            mHttpRequestInterFace.onFinish();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    if (mHttpRequestInterFace != null) {
                        mHttpRequestInterFace.onErrorResponse("下载失败！");
                        mHttpRequestInterFace.onFinish();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                if (mHttpRequestInterFace != null) {
                    if (error != null && !TextUtils.isEmpty(error.getMessage())) {
                        mHttpRequestInterFace.onErrorResponse(error.getMessage());
                    }
                    mHttpRequestInterFace.onFinish();
                }
            }

            @Override
            public void onCancel() {
                if (mHttpRequestInterFace != null) {
                    // 需要OnCancel
                    if (mHttpRequestInterFace instanceof IHttpRequestCancelInterFace) {
                        ((IHttpRequestCancelInterFace) mHttpRequestInterFace).onCancel();
                        mHttpRequestInterFace.onFinish();
                    }
                    // 不需要OnCancel
                    else if (mHttpRequestInterFace instanceof IHttpRequestInterFace) {
                        mHttpRequestInterFace.onErrorResponse(LibrariesCons.getContext().getResources().getString(R.string.info_error_cancelRequest));
                        mHttpRequestInterFace.onFinish();
                    }
                }
            }
        });
    }
}