package com.renrui.libraries.model.strand;

import javax.crypto.SecretKey;

/**
 * 登录相关加密用
 */
public class EncryptionInfoMode {

    public SecretKey secretKey = null;

    /**
     * 私钥
     */
    public String cipherkey = "";

    /**
     * 加密内容
     */
    public String ciphertext = "";
}
