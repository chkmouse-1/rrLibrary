package com.renrui.libraries.model.statistics;

import com.renrui.libraries.model.baseObject.BaseDataProvider;

/**
 * Created by xuelei on 2017/3/10.
 */
public class StatisticsLogBase extends BaseDataProvider {

    public String id = "";

    /**
     * 版本
     */
    public String ver = "";

    /**
     * 内容
     */
    public StatisticsLogBaseContent content = new StatisticsLogBaseContent();
}