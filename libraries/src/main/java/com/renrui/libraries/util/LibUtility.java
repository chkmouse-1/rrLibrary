package com.renrui.libraries.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.renrui.libraries.model.baseObject.BaseHttpModel;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibUtility {

    private static long lastClickTime;

    /**
     * 屏幕高度
     */
    private static int screenHeight = 0;
    /**
     * 屏幕宽度
     */
    private static int screenWidth = 0;

    /**
     * 初始化屏幕宽高
     */
    private static void initScreenSize() {
        try {
            // 初始化屏幕分辨率
            if (screenHeight == 0 || screenWidth == 0) {
                WindowManager wm = (WindowManager) LibrariesCons.getContext().getSystemService(Context.WINDOW_SERVICE);
                screenHeight = wm.getDefaultDisplay().getHeight();
                screenWidth = wm.getDefaultDisplay().getWidth();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        initScreenSize();

        return screenHeight;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        initScreenSize();

        return screenWidth;
    }

    /**
     * 通知栏高度
     */
    private static int statusHeight = 0;

    /**
     * 初始化通知栏高度
     */
    public static void initStatusHeight(Activity activity) {
        statusHeight = getStatusHeight(activity);
    }

    /**
     * 获取状态栏高度
     *
     * @return > 0 success; <= 0 fail
     */
    public static int getStatusHeight(Activity activity) {
        if (activity == null) {
            return -1;
        }

        int statusHeight;
        Class<?> localClass;
        try {
            Rect localRect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
            localClass = Class.forName("com.android.internal.R$dimen");
            Object localObject = localClass.newInstance();
            final int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
            statusHeight = activity.getResources().getDimensionPixelSize(i5);
        } catch (Exception e) {
            statusHeight = -1;
        }

        return statusHeight;
    }

    /**
     * 获取通知栏高度
     */
    public static int getStatusHeight() {
        return statusHeight;
    }

    /**
     * 防止连续点击 连续 返回TRUE 否则返回 FALSE
     */
    public static boolean isFastDoubleClick() {
        final long time = System.currentTimeMillis();
        final long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 150) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * 防止连续点击 连续 返回TRUE 否则返回 FALSE
     */
    public static boolean isFastDoubleClick(long value) {
        final long time = System.currentTimeMillis();
        final long timeD = time - lastClickTime;
        if (0 < timeD && timeD < value) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * dp转 px.
     *
     * @param value the value
     * @return the int
     */
    public static int dp2px(float value) {
        if (LibrariesCons.getContext() == null) {
            return 0;
        } else {
            final float scale = LibrariesCons.getContext().getResources().getDisplayMetrics().densityDpi;
            return (int) (value * (scale / 160) + 0.5f);
        }
    }

    /**
     * px转dp.
     *
     * @param value the value
     * @return the int
     */
    public static int px2dp(float value) {
        final float scale = LibrariesCons.getContext().getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    /**
     * 获取UUID
     */
    private static String getUUID(Context mContext) {
        String uniqueId;

        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

            String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            uniqueId = deviceUuid.toString();
        } catch (Exception ex) {
            uniqueId = "";
        }

        return uniqueId;
    }

    /**
     * @return 获取UUID
     */
    public static String getUUID() {
        String uuID = "";

        try {
            uuID = getLocalUUID();
            if (TextUtils.isEmpty(uuID)) {
                // 如果有READ_PHONE_STATE权限,则获取 UUID, 否则生成的唯一码
                String[] arrRequestPermission = UtilityPermission.getRequestPermission(Manifest.permission.READ_PHONE_STATE);
                if (arrRequestPermission == null || arrRequestPermission.length < 1) {
                    uuID = LibUtility.getUUID(LibrariesCons.getContext());
                } else {
                    uuID = getAppOnlySign(LibrariesCons.getContext());
                }

                setLocalUUID(uuID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return uuID;
    }

    /**
     * 优先读sd卡的uuid，其次读Preferences
     */
    public static String getLocalUUID() {

        String uuid = "";

        try {
            // 如果有读sd卡的权限，则优先读sd卡中的uuid
            final String[] arrRequestPermission = UtilityPermission.getRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (arrRequestPermission == null || arrRequestPermission.length < 1) {

                File file = new File(UtilityPermission.uuidDefaultPath);
                if (file.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String readline;
                    StringBuffer sb = new StringBuffer();
                    while ((readline = br.readLine()) != null) {
                        sb.append(readline);
                    }
                    br.close();

                    if (sb != null && !TextUtils.isEmpty(sb.toString())) {
                        uuid = new String(Base64.decode(sb.toString(), Base64.NO_PADDING));

                        // 如果解密字符串中没有'-'，代表解密失败
                        if (uuid.indexOf("-") != -1) {
                            uuid = "";
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (TextUtils.isEmpty(uuid)) {
            uuid = UtilitySharedPreferences.readString(UtilitySharedPreferences.string_app_uuid);
        }

        return uuid;
    }

    /**
     * 分别写入sd卡和Preferences
     */
    private static void setLocalUUID(String uuid) {
        try {
            // 如果有写入权限，并且sd卡允许读写   就写到sd中
            final String[] arrRequestPermission = UtilityPermission.getRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if ((arrRequestPermission == null || arrRequestPermission.length < 1) && Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
                File file = new File(UtilityPermission.uuidDefaultPath);
                FileOutputStream fos = new FileOutputStream(file);
                String info = Base64.encodeToString(uuid.getBytes(), Base64.NO_PADDING);
                fos.write(info.getBytes());
                fos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        UtilitySharedPreferences.writeString(UtilitySharedPreferences.string_app_uuid, uuid);
    }

    /**
     * 获取app唯一码
     */
    public synchronized static String getAppOnlySign(Context context) {
        String sign;

        File installation = new File(context.getFilesDir(), "INSTALLATION");
        try {
            if (!installation.exists()) {
                writeInstallationFile(installation);
            }

            sign = readInstallationFile(installation);
        } catch (Exception e) {
            e.printStackTrace();
            sign = "";
        }

        return sign;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

    /**
     * 比较两个对象是否相同
     */
    public static boolean equals(Object a, Object b) {
        boolean value = false;

        try {
            value = serializeToString(a).equals(serializeToString(b));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * 序列化对象
     */
    public static String serializeToString(Object obj) {
        String serStr;

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            serStr = "";
        }

        return serStr;
    }

    /**
     * 反序列化对象
     */
    public static Object deSerializationToObject(String str) {
        Object obj;
        try {
            String redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception ex) {
            obj = null;
        }

        return obj;
    }

    /**
     * 关闭软键盘
     */
    public static void CloseKeyBord(Activity activity) {

        try {
            View view = activity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 关闭软键盘
     */
    public static void CloseKeyBord(Activity activity, EditText editText) {

        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 打开软键盘
     */
    public static void OpenKeyBord(Activity activity, EditText editText) {

        try {
            // 调出软键盘
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 检测手机号是否合法 如果手机号合法，则返回true
     **/
    public static Boolean checkCellPhone(String cellPhone) {

        if (TextUtils.isEmpty(cellPhone)) {
            return false;
        }

        // 非 11位不合法
        if (cellPhone.trim().length() != 11) {
            return false;
        }

        try {
            Pattern pattern = Pattern.compile("^(([0-9][0-9][0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(cellPhone);
            return matcher.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 格式化手机号: 139-9999-9999
     * 13999999999
     **/
    public static String getDf1Phone(String cellPhone) {

        if (!checkCellPhone(cellPhone)) {
            return cellPhone;
        }

        String tempPhone;
        try {
            tempPhone = cellPhone.substring(0, 3) + "-" + cellPhone.substring(3, 7) + "-" + cellPhone.substring(7, cellPhone.length());
        } catch (Exception ex) {
            ex.printStackTrace();
            tempPhone = cellPhone;
        }

        return tempPhone;
    }

    /**
     * Email是否合法
     */
    public static boolean checkMailFormat(String email) {
        String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return email.matches(reg);
    }

    /**
     * MD5 加密字符串
     *
     * @param info 字符串
     */
    public static String getMD5(String info) {

        if (TextUtils.isEmpty(info)) {
            return "";
        }

        StringBuffer strBuf = new StringBuffer();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            strBuf = new StringBuffer();
        }

        return strBuf.toString();
    }

    /**
     * urlEncode
     */
    public static String urlEncode(String content) {

        String result = "";

        if (TextUtils.isEmpty(content)) {
            return result;
        }

        try {
            result = URLEncoder.encode(content.trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            result = "";
        }

        return result;
    }

    /**
     * urlDecode
     */
    public static String urlDecode(String content) {

        if (TextUtils.isEmpty(content)) {
            return "";
        }

        try {
            return URLDecoder.decode(content, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {

        boolean value = false;

        try {
            final int len = source.length();
            char codePoint;
            for (int i = 0; i < len; i++) {
                codePoint = source.charAt(i);

                // 如果不能匹配,则该字符是Emoji表情
                if (!isEmojiCharacter(codePoint)) {
                    value = true;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            value = false;
        }

        return value;
    }

    /**
     * 判断是否是Emoji 比较的单个字符
     *
     * @param codePoint 字符
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 确认字符串是否为email格式
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(strEmail);
        //进行正则匹配
        return m.matches();
    }

    /**
     * Base64编码
     */
    public static String base64Encode(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Base64解码
     */
    public static String base64Decode(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static RequestParams getUrlParams(BaseHttpModel httpModel) {
        RequestParams params = new RequestParams();

        try {
            // 获取所有字段
            java.lang.reflect.Field[] fields = httpModel.getClass().getFields();
            for (int i = 0; i < fields.length; i++) {

                // file
                if (fields[i].getType().getName().equalsIgnoreCase("java.io.File")) {
                    if (fields[i].get(httpModel) != null) {
                        try {
                            File file = (File) fields[i].get(httpModel);
                            params.put(fields[i].getName(), file);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                // string
                else if (fields[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
                    if (fields[i].get(httpModel) != null && !TextUtils.isEmpty(fields[i].get(httpModel).toString())) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
                // int
                else if (fields[i].getType().getName().equalsIgnoreCase("int") || fields[i].getType().getName().equalsIgnoreCase("java.lang.Integer")) {
                    if (fields[i].get(httpModel) != null) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
                // boolean
                else if (fields[i].getType().getName().equalsIgnoreCase("boolean") || fields[i].getType().getName().equalsIgnoreCase("java.lang.Boolean")) {
                    if (fields[i].get(httpModel) != null && !TextUtils.isEmpty(fields[i].get(httpModel).toString())) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
                // float
                else if (fields[i].getType().getName().equalsIgnoreCase("float") || fields[i].getType().getName().equalsIgnoreCase("java.lang.Float")) {
                    if (fields[i].get(httpModel) != null && !TextUtils.isEmpty(fields[i].get(httpModel).toString())) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
                // double
                else if (fields[i].getType().getName().equalsIgnoreCase("double") || fields[i].getType().getName().equalsIgnoreCase("java.lang.Double")) {
                    if (fields[i].get(httpModel) != null && !TextUtils.isEmpty(fields[i].get(httpModel).toString())) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
                // long
                else if (fields[i].getType().getName().equalsIgnoreCase("long") || fields[i].getType().getName().equalsIgnoreCase("java.lang.Long")) {
                    if (fields[i].get(httpModel) != null && !TextUtils.isEmpty(fields[i].get(httpModel).toString())) {
                        params.put(fields[i].getName(), fields[i].get(httpModel).toString());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return params;
    }

    public static String getManifestMetaDataValue(String noteName) {
        String value = "";

        try {
            ApplicationInfo appInfo = LibrariesCons.getContext().getPackageManager().getApplicationInfo(LibrariesCons.getContext().getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(noteName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }
}