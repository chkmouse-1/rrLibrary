package com.renrui.libraries.util;

import com.renrui.libraries.enumDef.ClassType;

import java.io.Serializable;
import java.util.ArrayList;

public class UtilityClassInfo {


    public static boolean isArraryList(Object obj) {
        boolean isArraryList = false;

        if (obj == null)
            return isArraryList;

        try {
            isArraryList = UtilitySecurity.equalsIgnoreCase(obj.getClass().getName(), "java.util.ArrayList");
        } catch (Exception ex) {
            ex.printStackTrace();
            isArraryList = false;
        }

        return isArraryList;
    }

    /**
     * 是否是arraryList<String>类型
     * <p>
     * 如果list.size() == 0, 返回false
     */
    public static boolean isArraryString(Object obj) {
        boolean isArraryString = false;

        if (obj == null)
            return isArraryString;

        try {
            if (!UtilitySecurity.equalsIgnoreCase(obj.getClass().getName(), "java.util.ArrayList")) {
                return isArraryString;
            }

            ArrayList<?> alObj = (ArrayList<?>) obj;
            int i = 0;
            for (Object object : alObj) {
                if (i > 0)
                    break;

                isArraryString = object instanceof String;

                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isArraryString = false;
        }

        return isArraryString;
    }

    /**
     * 是否是arraryList<Integer>类型
     * <p>
     * 如果list.size() == 0, 返回false
     */
    public static boolean isArraryInteger(Object obj) {
        boolean isArraryInteger = false;

        if (obj == null)
            return isArraryInteger;

        try {
            if (!UtilitySecurity.equalsIgnoreCase(obj.getClass().getName(), "java.util.ArrayList")) {
                return isArraryInteger;
            }

            ArrayList<?> alObj = (ArrayList<?>) obj;
            int i = 0;
            for (Object object : alObj) {
                if (i > 0)
                    break;

                isArraryInteger = object instanceof Integer;

                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isArraryInteger = false;
        }

        return isArraryInteger;
    }

    public static int getClassType(Object obj) {
        int classType;

        try {
            // string
            if (obj instanceof String) {
                classType = ClassType.STRING;
            }
            // int
            else if (obj instanceof Integer) {
                classType = ClassType.INT;
            }
            // float
            else if (obj instanceof Float) {
                classType = ClassType.FLOAT;
            }
            // double
            else if (obj instanceof Double) {
                classType = ClassType.DOUBLE;
            }
            // serializable
            else if (obj instanceof Serializable) {
                classType = ClassType.SERIALIZABLE;
            }
            // arraryList<integer>
            else if (isArraryInteger(obj)) {
                classType = ClassType.ARRARYLIST_INTEGER;
            }
            // arraryList<string>
            else if (isArraryString(obj)) {
                classType = ClassType.ARRARYLIST_STRING;
            } else {
                classType = ClassType.Other;
            }
        } catch (Exception ex) {
            classType = ClassType.Other;
        }

        return classType;
    }
}
