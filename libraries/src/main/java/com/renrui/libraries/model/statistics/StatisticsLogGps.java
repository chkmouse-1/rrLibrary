package com.renrui.libraries.model.statistics;

/**
 * GPS定位 实时日志
 */
public class StatisticsLogGps extends StatisticsLogBaseContent {
    public String city = "";//城市
    public String address = "";//:"中关村文焕创意产业园", // 详细地址
    public double latitude; //纬度
    public double longitude;//经度
    public double altitude;//海拔
}