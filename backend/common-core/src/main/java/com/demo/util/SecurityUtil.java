package com.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author gs
 * @Date 2022-08-12 09:55
 * @Description 安全工具类
 *                      AES参考：  https://blog.csdn.net/liyuan0714/article/details/123084002
 *                                https://www.cnblogs.com/andy1234/p/16008021.html
 *
 */
@Slf4j
public class SecurityUtil {


// ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~     base64  加解密     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~


    /**
     * base64加密
     * @param data 原始数据
     */
    public static String base64Encrypt(String data) {
        if (data == null || data.trim().isEmpty()) {
             return "";
        }
        return new BASE64Encoder().encode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64解密
     * @param encodeData 加密数据
     */
    public static String base64Decrypt(String encodeData) {
        if (encodeData == null || encodeData.trim().isEmpty()) {
            return "";
        }
        try {
            byte[] bt = new BASE64Decoder().decodeBuffer(encodeData);
            return new String(bt);
        } catch (Exception e) {
            log.error("【base64】解密异常", e);
            return "";
        }
    }





// ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~      AES  加解密     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

    /** 加解密算法/工作模式/填充方式 */
    static String AES_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /** AES 加密密钥 */
    static String AES_KEY = "salt";

    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //生成指定算法密钥的生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(128, random);
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    /**
     * AES加密操作
     *
     * @param data  待加密内容
     */
    public static String aesEncrypt(String data) {
        if (data == null || data.trim().isEmpty()) {
            log.error("AES encryption params is null");
            return null;
        }
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
            //初始化为加密密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(AES_KEY));
            byte[] encryptByte = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            log.error("AES encryption operation has exception,content:{}", data, e);
            return null;
        }
    }

    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     */
    public static String aesDecrypt(String encryptContent) {
        if (encryptContent == null || encryptContent.trim().isEmpty()) {
            log.error("AES decryption params is null");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(AES_KEY));
            //执行解密操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AES decryption operation has exception,content:{},password:{}", encryptContent, e);
            return null;
        }
    }



// ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~      MD5  加密     ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

    /**
     * md5加密
     * @param data 待加密数据
     */
    public static String md5Encrypt(String data)  {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        // 16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
