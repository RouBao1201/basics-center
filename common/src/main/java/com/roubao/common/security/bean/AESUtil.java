package com.roubao.common.security.bean;

import cn.hutool.core.util.StrUtil;
import com.roubao.common.security.properties.AesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密工具
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@Slf4j
public class AESUtil {
    /**
     * AES密钥标识
     */
    private static final String SIGN_AES = "AES";

    /**
     * 密码器AES模式
     */
    private static final String CIPHER_AES = "AES/ECB/PKCS5Padding";

    private final AesProperties aesProperties;

    public AESUtil(AesProperties aesProperties) {
        this.aesProperties = aesProperties;
    }

    /**
     * 加密
     *
     * @param clearStr 明文
     * @return 密文
     */
    public String encrypt(String clearStr) {
        return encrypt(clearStr, this.aesProperties.getKey());
    }

    /**
     * 解密
     *
     * @param cipherStr 密文
     * @return 明文
     */
    public String decrypt(String cipherStr) {
        return decrypt(cipherStr, this.aesProperties.getKey());
    }

    /**
     * 校验密文是否正确
     *
     * @param cipherStr 密文
     * @param clearStr  明文
     * @return 校验结果
     */
    public boolean verify(String cipherStr, String clearStr) {
        if (StrUtil.isEmpty(cipherStr) || StrUtil.isEmpty(clearStr)) {
            return false;
        }
        return clearStr.equals(decrypt(cipherStr));
    }

    /**
     * 校验密文是否正确
     *
     * @param cipherStr 密文
     * @param clearStr  明文
     * @param key       密钥
     * @return 校验结果
     */
    public boolean verify(String cipherStr, String clearStr, String key) {
        if (StrUtil.isEmpty(cipherStr)
                || StrUtil.isEmpty(clearStr)
                || StrUtil.isEmpty(key)) {
            return false;
        }
        return clearStr.equals(decrypt(cipherStr, key));
    }

    /**
     * 加密
     *
     * @param clearStr 明文
     * @param key      密钥
     * @return 密文
     */
    public String encrypt(String clearStr, String key) {
        if (StrUtil.isEmpty(key)) {
            log.error("AESUtil ==> AesKey is empty. Please check your config.");
            return "";
        }
        byte[] decodedKey = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, SIGN_AES);
        byte[] encrypted = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encrypted = cipher.doFinal(clearStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("AESUtil ==> Encrypt error.ErrorMessage:{}", e.getMessage());
        }
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 解密
     *
     * @param cipherStr 密文
     * @param key       密钥
     * @return 明文
     */
    public String decrypt(String cipherStr, String key) {
        byte[] decodedKey = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, SIGN_AES);
        byte[] original = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            original = cipher.doFinal(Base64.decodeBase64(cipherStr));
        } catch (Exception e) {
            log.error("AESUtil ==> Decrypt error. ErrorMessage:{}", e.getMessage());
        }
        return new String(original, StandardCharsets.UTF_8);
    }

    /**
     * 生成密钥
     *
     * @param keySize 密钥大小
     * @return 密钥
     */
    public String generateSecretKey(KeySize keySize) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(SIGN_AES);
        } catch (NoSuchAlgorithmException e) {
            log.error("AESUtil ==> GenerateSecretKey error. ErrorMessage:{}", e.getMessage());
            return "";
        }
        keyGenerator.init(keySize.getSize());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        return new String(Base64.encodeBase64(keyBytes), StandardCharsets.UTF_8);
    }

    public enum KeySize {
        SIZE_128(128),

        SIZE_192(192),

        SIZE_256(256);

        private final int size;

        public int getSize() {
            return size;
        }

        KeySize(int size) {
            this.size = size;
        }
    }
}
