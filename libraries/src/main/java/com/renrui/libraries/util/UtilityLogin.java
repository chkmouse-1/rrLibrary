package com.renrui.libraries.util;

import android.text.TextUtils;
import android.util.Base64;

import com.loopj.android.http.RequestParams;
import com.renrui.libraries.model.strand.EncryptionInfoMode;
import com.renrui.libraries.model.strand.PhoneLoginModel;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UtilityLogin {

    public static EncryptionInfoMode getEncryptionInfoMode(PhoneLoginModel model) {

        EncryptionInfoMode encryptionInfoMode = new EncryptionInfoMode();

        try {
            // 加密登录信息
            final String text = mHttpClient.GetGsonInstance().toJson(model, PhoneLoginModel.class);
            encryptionInfoMode = UtilityLogin.getEncryptionModel(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encryptionInfoMode;
    }

    public static EncryptionInfoMode getEncryptionInfoMode(String text) {

        EncryptionInfoMode encryptionInfoMode = new EncryptionInfoMode();

        try {
            // 加密登录信息
            encryptionInfoMode = UtilityLogin.getEncryptionModel(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encryptionInfoMode;
    }

    public static RequestParams getEncryRequestParams(EncryptionInfoMode model) {
        RequestParams para = null;

        try {
            para = new RequestParams();
            para.put("cipherkey", model.cipherkey);
            para.put("ciphertext", model.ciphertext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return para;
    }

    /**
     * 加密
     */
    public static EncryptionInfoMode getEncryptionModel(String text) {

        EncryptionInfoMode loginInfoMode = new EncryptionInfoMode();

        if (TextUtils.isEmpty(text)) {
            return loginInfoMode;
        }

        try {
            /* 生成会话密钥 */
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");    // 使用AES加密算法
            keyGenerator.init(128);                     // 使用128位长度的密钥
            loginInfoMode.secretKey = keyGenerator.generateKey();     // 生成密钥

            // 通信报文，例如用户账号密码信息
            byte[] plaintext = text.getBytes();
            byte[] pubk = Base64.decode(LibrariesCons.AboutLoginPublicKey.getBytes(), Base64.NO_WRAP);    // 公钥（PEM格式），预置在App中

            // 加密报文
            Cipher secretKeyCipher = Cipher.getInstance("AES");    // 使用AES加密算法
            secretKeyCipher.init(Cipher.ENCRYPT_MODE, loginInfoMode.secretKey);    // 加密模式
            byte[] ciphertext = secretKeyCipher.doFinal(plaintext);

            // 读取公钥
            KeyFactory kf = KeyFactory.getInstance("RSA");    // 使用RSA算法
            X509EncodedKeySpec x509ks = new X509EncodedKeySpec(pubk);
            PublicKey publicKey = kf.generatePublic(x509ks);

            // 用公钥对密钥进行加密
            Cipher publicKeyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");    // 使用RSA算法
            publicKeyCipher.init(Cipher.ENCRYPT_MODE, publicKey);   // 加密模式
            byte[] plainkey = loginInfoMode.secretKey.getEncoded();         // 密钥明文
            byte[] cipherkey = publicKeyCipher.doFinal(plainkey);   // 密钥密文

            // 把加密后的密钥cipherkey和密文ciphertext发送给服务器
            loginInfoMode.ciphertext = new String(Base64.encode(ciphertext, Base64.NO_WRAP));
            loginInfoMode.cipherkey = new String(Base64.encode(cipherkey, Base64.NO_WRAP));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return loginInfoMode;
    }

    /**
     * 解密服务器加密的字符串
     *
     * @param ciphertext ciphertext
     * @param secretKey  secretKey
     */
    public static String serverDecryption(String ciphertext, SecretKey secretKey) {
        // 解密用户信息
        byte[] decryptedtext;
        String loginResponse;

        try {
            Cipher secretKeyCipher = Cipher.getInstance("AES");     // 使用AES算法

            secretKeyCipher.init(Cipher.DECRYPT_MODE, secretKey);   // 解密模式
            decryptedtext = secretKeyCipher.doFinal(Base64.decode(ciphertext.getBytes(), Base64.NO_WRAP));
            loginResponse = new String(decryptedtext);
        } catch (Exception ex) {
            ex.printStackTrace();
            loginResponse = "";
        }

        return loginResponse;
    }

    /**
     * 解密服务器加密的字符串
     *
     * @param ciphertext ciphertext
     * @param secretKey  secretKey
     */
    public static String serverDecryptionNew(String ciphertext, SecretKey secretKey) {
        // 解密用户信息
        byte[] decryptedtext;
        String loginResponse;

        try {
//            Cipher secretKeyCipher = Cipher.getInstance("AES");     // 使用AES算法
            Cipher secretKeyCipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");

            secretKeyCipher.init(Cipher.DECRYPT_MODE, secretKey);   // 解密模式
            decryptedtext = secretKeyCipher.doFinal(Base64.decode(ciphertext.getBytes(), Base64.NO_WRAP));
            loginResponse = new String(decryptedtext);
        } catch (Exception ex) {
            ex.printStackTrace();
            loginResponse = "";
        }

        return loginResponse;
    }
}
