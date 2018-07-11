//package com.renrui.libraries.util;
//
//import com.renrui.job.application.RRApplication;
//import com.renrui.job.constant.AppConfig;
//import com.renrui.job.model.httpModel.HttpLogSendInstantHttpModelJob;
//import com.renrui.libraries.interfaces.IHttpRequestInterFace;
//import com.renrui.libraries.model.statistics.LoginMessageStat;
//import com.renrui.libraries.model.statistics.StatisticsLogBase;
//import com.renrui.libraries.model.statistics.StatisticsLogBaseContent;
//import com.renrui.libraries.model.statistics.StatisticsLogException;
//import com.renrui.libraries.model.statistics.StatisticsLogStartup;
//
///**
// * 实时日志
// */
//public class UtilityHttpLog {
//
//    private static String Tag = "httplog";
//
//    /**
//     * 发送实时日志
//     *
//     * @param content 内容
//     */
//    private static void sendSelf(String id, String content) {
//
//        try {
//            // 整体消息
//            StatisticsLogBase statisticsLogBase = new StatisticsLogBase();
//            statisticsLogBase.id = id;
//            statisticsLogBase.ver = AppConfig.httpLogVersion;
//
//            LoginMessageStat loginMessageStat = new LoginMessageStat();
//            loginMessageStat.content = content;
//
//            statisticsLogBase.content = loginMessageStat;
//
//            sendLog(statisticsLogBase, null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 发送自定义错误日志
//     */
//    public static void sendExceptionLog(String name, StatisticsLogException model, IHttpRequestInterFace iHttpRequestInterFace) {
//
//        try {
//            // 整体消息
//            StatisticsLogBase statisticsLogBase = new StatisticsLogBase();
//            statisticsLogBase.id = name;
//            statisticsLogBase.ver = AppConfig.httpLogVersion;
//
//            LoginMessageStat loginMessageStat = new LoginMessageStat();
//            loginMessageStat.content = mHttpClient.GetGsonInstance().toJson(model);
//
//            statisticsLogBase.content = loginMessageStat;
//
//            sendLog(statisticsLogBase, iHttpRequestInterFace);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 发送实时日志
//     *
//     * @param obj 内容
//     */
//    public static void sendGetviewException(Object obj) {
//
//        try {
//            sendSelf("android_getview_exception", mHttpClient.GetGsonInstance().toJson(obj));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 发送启动实时日志
//     *
//     * @param entryType 进入类型
//     * @param content   内容
//     */
//    public static void sendStartup(String entryType, String content) {
//
//        try {
//            // 整体消息
//            StatisticsLogBase statisticsLogBase = new StatisticsLogBase();
//            statisticsLogBase.id = "startup";
//            statisticsLogBase.ver = AppConfig.httpLogVersion;
//
//            StatisticsLogStartup contentModel = new StatisticsLogStartup();
//            contentModel.type = entryType;
//            // 网络类型
//            contentModel.network = UtilityNetWork.getInstance().GetNetworkType();
//            // 如果wifi连接，取wifi的名称
//            // 否则取运营商的名称
//            if (contentModel.network.equalsIgnoreCase("wifi")) {
//                contentModel.networkName = UtilityNetWork.getInstance().getWifiName();
//            } else {
//                contentModel.networkName = UtilityNetWork.getInstance().getNetOperatorName();
//            }
//            // ip地址
//            contentModel.ip = UtilityNetWork.getInstance().getIPAddress();
//            contentModel.content = content;
//
//            statisticsLogBase.content = contentModel;
//
//            sendLog(statisticsLogBase, null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
////    /**
////     * 发送Gps定位成功日志 (app只发送一次)
////     */
////    public static void sendGps(final StatisticsLogBaseContent model) {
////
////        if (model == null) {
////            return;
////        }
////
////        // 只发送一次
////        if (EditSharedPreferences.getIsSendHttpGpsLog()) {
////            return;
////        }
////
////        try {
////            // 整体消息
////            StatisticsLogBase statisticsLogBase = new StatisticsLogBase();
////            statisticsLogBase.id = "gps";
////            statisticsLogBase.ver = AppConfig.httpLogVersion;
////            statisticsLogBase.content = model;
////
////            sendLog(statisticsLogBase, new IHttpRequestInterFace() {
////                @Override
////                public void onStart() {
////                    Logger.getInstance().e(Tag, "开始发送定位实时日志。");
////                }
////
////                @Override
////                public void onResponse(String content) {
////                    Logger.getInstance().e(Tag, "发送定位实时日志成功");
////                    EditSharedPreferences.setIsSendHttpGpsLog(true);
////                }
////
////                @Override
////                public void onErrorResponse(String content) {
////                    Logger.getInstance().e(Tag, "发送定位实时日志错误");
////                }
////
////                @Override
////                public void onFinish() {
////                    Logger.getInstance().e(Tag, "发送定位实时日志结束");
////                }
////            });
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    /**
////     * 发送Gps定位成功日志 (每次启动都发)
////     */
////    public static void sendAutoGps(final StatisticsLogBaseContent model) {
////
////        if (model == null) {
////            return;
////        }
////
////        try {
////            // 整体消息
////            StatisticsLogBase statisticsLogBase = new StatisticsLogBase();
////            statisticsLogBase.id = "autogps";
////            statisticsLogBase.ver = AppConfig.httpLogVersion;
////            statisticsLogBase.content = model;
////
////            sendLog(statisticsLogBase, new IHttpRequestInterFace() {
////                @Override
////                public void onStart() {
////                    Logger.getInstance().e(Tag, "开始发送autogps实时日志。");
////                }
////
////                @Override
////                public void onResponse(String content) {
////                    Logger.getInstance().e(Tag, "发送autogps实时日志成功");
////                    EditSharedPreferences.setIsSendHttpGpsLog(true);
////                }
////
////                @Override
////                public void onErrorResponse(String content) {
////                    Logger.getInstance().e(Tag, "发送autogps实时日志错误");
////                }
////
////                @Override
////                public void onFinish() {
////                    Logger.getInstance().e(Tag, "发送autogps实时日志结束");
////                }
////            });
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    /**
//     * 发送日志
//     *
//     * @param model                 model
//     * @param iHttpRequestInterFace Http回调
//     */
//    private static void sendLog(StatisticsLogBase model, IHttpRequestInterFace iHttpRequestInterFace) {
//        if (model == null) {
//            return;
//        }
//
//        if (iHttpRequestInterFace == null) {
//            iHttpRequestInterFace = new IHttpRequestInterFace() {
//                @Override
//                public void onStart() {
//                    Logger.getInstance().e(Tag, "开始发送实时日志。");
//                }
//
//                @Override
//                public void onResponse(String content) {
//                    Logger.getInstance().e(Tag, "发送实时日志成功：" + content);
//                }
//
//                @Override
//                public void onErrorResponse(String content) {
//                    Logger.getInstance().e(Tag, "发送实时日志失败：" + content);
//                }
//
//                @Override
//                public void onFinish() {
//                    Logger.getInstance().e(Tag, "发送实时日志结束。");
//                }
//            };
//        }
//
//        try {
//            String json = mHttpClient.GetGsonInstance().toJson(model);
//            Logger.getInstance().e(Tag, "实时日志内容：", json);
//
//            HttpLogSendInstantHttpModelJob httpModel = new HttpLogSendInstantHttpModelJob();
//            httpModel.setIsPostJson(true);
//            httpModel.setPostJsonText(json);
//            mHttpClient.Request(RRApplication.getAppContext(),httpModel,iHttpRequestInterFace);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}