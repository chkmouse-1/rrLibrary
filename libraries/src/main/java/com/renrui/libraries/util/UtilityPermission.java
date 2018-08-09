package com.renrui.libraries.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.0 权限相关
 */
public class UtilityPermission {

    /**
     * uuid默认路径
     */
    public final static String uuidDefaultPath = Environment.getExternalStorageDirectory() + "/renrui/uuid.tm";

    public interface IRequestPermission {
        void onSuccess(int requestCode);

        void onFaild(int requestCode);
    }

    /**
     * 请求写入权限（否则广告页无法下载）
     */
    public static void requestWritePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            final String[] arrRequestPermission = UtilityPermission.getRequestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (arrRequestPermission.length > 0) {
                ActivityCompat.requestPermissions(activity, arrRequestPermission, RequestPermissions.REQUEST_CODE_Write);
            }
        }
    }

//    /**
//     * 拨号 (6.0之前直接拨号，6.0以后去拨号页)
//     *
//     * @param phone 手机号
//     */
//    public static void toCall(Activity activity, String phone) {
//        if (activity == null || TextUtils.isEmpty(phone)) {
//            return;
//        }
//
//        try {
//            Intent intent;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                intent = new Intent();
//                intent.setAction(Intent.ACTION_CALL);
//                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
//            } else {
//                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
//            }
//
//            activity.startActivity(intent);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * 当前应用都拥有哪些权限
     *
     * @param args 检查的权限（多个）
     * @return 拥有的权限(多个)
     */
    public static String[] getRequestPermission(Activity activity, String... args) {
        String[] arr;

        try {
            List<String> lis = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, args[i]) != PackageManager.PERMISSION_GRANTED) {
                    lis.add(args[i]);
                }
            }

            arr = new String[lis.size()];
            for (int i = 0; i < lis.size(); i++) {
                arr[i] = lis.get(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            arr = new String[0];
        }

        return arr;
    }

    /**
     * 当前应用是否都拥有哪些权限
     *
     * @param args 检查的权限（多个）
     * @return 拥有的权限(多个)
     */
    public static String[] getRequestPermission(String... args) {
        String[] arr;

        try {
            List<String> lis = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (ContextCompat.checkSelfPermission(LibrariesCons.getContext(), args[i]) != PackageManager.PERMISSION_GRANTED) {
                    lis.add(args[i]);
                }
            }

            arr = new String[lis.size()];
            for (int i = 0; i < lis.size(); i++) {
                arr[i] = lis.get(i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            arr = new String[0];
        }

        return arr;
    }

    /**
     * 权限是否全通过
     */
    public static boolean isAllAgree(int[] grantResults) {
        boolean isAllAgree = true;

        try {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    isAllAgree = false;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isAllAgree;
    }

    /**
     * 权限是否全部拒绝
     */
    public static boolean isAllRefuse(int[] grantResults) {
        boolean isAllRefuse = true;

        try {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    isAllRefuse = false;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isAllRefuse;
    }

    /**
     * 某个权限是否通过
     *
     * @param permissions        permissions
     * @param grantResults       grantResults
     * @param requestPermissions requestPermissions
     */
    public static boolean isAgree(String[] permissions, int[] grantResults, String requestPermissions) {
        boolean isAgree = true;

        try {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(requestPermissions)) {
                    isAgree = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isAgree;
    }
}