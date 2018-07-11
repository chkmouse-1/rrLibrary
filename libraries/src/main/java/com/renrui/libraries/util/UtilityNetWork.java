package com.renrui.libraries.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * 网络类
 */
public class UtilityNetWork {

//    private static UtilityNetWork instance = null;
//    private static Context context = null;
//
//    public static void setContext(Context context) {
//        UtilityNetWork.context = context;
//    }
//
//    public static UtilityNetWork getInstance() {
//        if (instance == null) {
//            instance = new UtilityNetWork();
//        }
//
//        return instance;
//    }

    /**
     * 判断当前网络是否是wifi网络
     */
    public static boolean netWorkIsWifi() {

        boolean value = false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) LibrariesCons.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

            value = activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * 当前网络连接是否可用
     */
    public static boolean isNetworkAvailable() {

        boolean isAvailable;

        try {
            ConnectivityManager cm = (ConnectivityManager)  LibrariesCons.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm == null) {
                isAvailable = false;
            } else {
                isAvailable = cm.getActiveNetworkInfo().isAvailable();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isAvailable = false;
        }

        return isAvailable;
    }

    /**
     * 获取当前机器的IP地址
     */
    public static String getIPAddress() {

        String ipAddress = "";

        try {
            NetworkInfo info = ((ConnectivityManager) LibrariesCons.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                    try {
                        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                            NetworkInterface intf = en.nextElement();
                            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                                InetAddress inetAddress = enumIpAddr.nextElement();
                                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                    return inetAddress.getHostAddress();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                    WifiManager wifiManager = (WifiManager)  LibrariesCons.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                }
            } else {
                //当前无网络连接,请在设置中打开网络
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ipAddress;
    }

    /**
     * 获取网络类型，  0 - 无网络; 1 - 2G; 2 - 3G; 3 - 4G; 5 - WIFI
     */
    public static String GetNetworkType() {
        String strNetworkType = "";

        ConnectivityManager manager = (ConnectivityManager)  LibrariesCons.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "wifi";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2g";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3g";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4g";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3g";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }
            }
        }

        return strNetworkType;
    }

    /**
     * 获取运营商名称
     */
    public static String getNetOperatorName() {

        String netOperator = "";

        try {
            TelephonyManager telManager = (TelephonyManager)  LibrariesCons.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            final String operator = telManager.getSimOperator();
            if (!TextUtils.isEmpty(operator)) {
                if (operator.equals("46000") || operator.equals("46002")) {
                    netOperator = "中国移动";
                } else if (operator.equals("46001")) {
                    netOperator = "中国联通";
                } else if (operator.equals("46003")) {
                    netOperator = "中国电信";
                } else {
                    netOperator = "未知";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            netOperator = "未知";
        }

        return netOperator;
    }

    /**
     * 获取当前wifi名称
     */
    public static String getWifiName() {
        String wifiName;

        try {
            WifiManager wifiMgr = (WifiManager)  LibrariesCons.getContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiMgr.getConnectionInfo();
            wifiName = info != null ? info.getSSID() : "";
            wifiName = wifiName.replace('"', ' ').trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            wifiName = "";
        }

        return wifiName;
    }

    /**
     * 是否是合法IP地址
     *
     * @param addr Address
     */
    public static boolean isIP(String addr) {
        if (TextUtils.isEmpty(addr) || addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }

        try {
            Pattern pattern = Pattern
                    .compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
                            + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
            return pattern.matcher(addr).matches();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip IP
     */
    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}