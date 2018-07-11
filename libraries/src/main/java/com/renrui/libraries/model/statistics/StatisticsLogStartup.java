package com.renrui.libraries.model.statistics;

/**
 * 启动类型
 */
public class StatisticsLogStartup extends StatisticsLogBaseContent {

    public String type = ""; //    //启动渠道：push（推送）im（im聊天打开）other（其他途径打开） normal (正常渠道打开) assistant（小秘书打开）
    public String network = ""; //: "WWAN",   //网络环境：WWAN(移动网络)WiFi: (局域网)
    public String networkName = ""; //:"中国联通" ,  // 网络名称
    public String ip = "";
    public String content = "";//   // 启动内容，push消息。im消息。其他渠道的链接，小秘书内容
}