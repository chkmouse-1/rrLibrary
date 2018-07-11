package com.renrui.libraries.enumDef;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 星座
 */
public class Constellation {

    /**
     * 水瓶座
     */
    public static final String shuiping = "水瓶座";

    /**
     * 双鱼座
     */
    public static final String shuangyu = "双鱼座";

    /**
     * 白羊座
     */
    public static final String baiyang = "白羊座";

    /**
     * 金牛座
     */
    public static final String jinniu = "金牛座";

    /**
     * 双子座
     */
    public static final String shuangzi = "双子座";

    /**
     * 巨蟹座
     */
    public static final String juxie = "巨蟹座";

    /**
     * 狮子座
     */
    public static final String shizi = "狮子座";

    /**
     * 处女座
     */
    public static final String chunv = "处女座";

    /**
     * 天秤座
     */
    public static final String tianping = "天秤座";

    /**
     * 天蝎座
     */
    public static final String tianxie = "天蝎座";

    /**
     * 射手座
     */
    public static final String sheshou = "射手座";

    /**
     * 魔羯座
     */
    public static final String mojie = "魔羯座";


    @StringDef({shuiping, shuangyu, baiyang,jinniu,shuangzi,juxie,shizi,chunv,tianping,tianxie,sheshou,mojie})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Def {
    }
}