package com.renrui.libraries.model.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常
 */
public class StatisticsLogException extends StatisticsLogBaseContent {

    /**
     * 类名
     */
    public String className = "";

    /**
     * 身份
     */
    public String statue = "";

    /**
     * 用户ID
     */
    public String userID = "";

    /**
     * userAgent
     */
    public String ua = "";

    public long time = 0L;

    public String content = "";

    /**
     * activity堆栈
     */
    public List<String> lisActivity = new ArrayList<>();
}