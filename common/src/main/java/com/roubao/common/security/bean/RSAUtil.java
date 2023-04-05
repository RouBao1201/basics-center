package com.roubao.common.security.bean;

import cn.hutool.core.util.StrUtil;
import com.roubao.common.security.properties.RsaProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密工具
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@Slf4j
public class RSAUtil {
    private static final String SIGN_RSA = "RSA";

    private final RsaProperties rsaProperties;

    public RSAUtil(RsaProperties rsaProperties) {
        this.rsaProperties = rsaProperties;
    }

    /**
     * 随机生成密钥对
     */
    public Map<String, String> generateKeyPair() {
        // 密钥生成器
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance(SIGN_RSA);
        } catch (NoSuchAlgorithmException e) {
            log.error("RSAUtil ==> GenerateKeyPair error[NoSuchAlgorithmException]. ErrorMessage:{}", e.getMessage());
            return new HashMap<>(16);
        }

        // 设置为2048位长度
        generator.initialize(2048, new SecureRandom());

        // 生成密钥对
        KeyPair keyPair = generator.generateKeyPair();

        // 获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 进行64位编码得到公钥的字符串
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));

        // 进行64位编码得到私钥的字符串
        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));

        // 将公钥和私钥保存到Map
        HashMap<String, String> keyMap = new HashMap<>(16);
        keyMap.put(Key.PUBLIC_KEY, publicKeyString);
        keyMap.put(Key.PRIVATE_KEY, privateKeyString);
        return keyMap;
    }

    /**
     * 加密
     *
     * @param clearStr 需要加密的密码
     * @return 加密后的密码
     */
    public String encrypt(String clearStr) {
        return encrypt(clearStr, this.rsaProperties.getPublicKey());
    }

    /**
     * 解密
     *
     * @param cipherStr 密文
     * @return 明文
     */
    public String decrypt(String cipherStr) {
        return decrypt(cipherStr, this.rsaProperties.getPrivateKey());
    }

    /**
     * 加密
     *
     * @param clearStr  需要加密的密码
     * @param publicKey 公钥
     * @return 加密后的密码
     */
    public String encrypt(String clearStr, String publicKey) {
        if (StrUtil.isEmpty(clearStr) || StrUtil.isEmpty(publicKey)) {
            log.error("RSAUtil ==> Encrypt error. The clearStr[{}] and publicKey[{}] not be empty.", clearStr, publicKey);
            return "";
        }
        byte[] decoded = Base64.decodeBase64(publicKey);
        byte[] bytes;
        try {
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(SIGN_RSA).generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(SIGN_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            bytes = cipher.doFinal(clearStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("RSAUtil ==> Encrypt error. ErrorMessage:{}", e.getMessage());
            return "";
        }
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 解密
     *
     * @param cipherStr  密文
     * @param privateKey 密钥
     * @return 明文
     */
    public String decrypt(String cipherStr, String privateKey) {
        if (StrUtil.isEmpty(cipherStr) || StrUtil.isEmpty(privateKey)) {
            log.error("RSAUtil ==> Decrypt error. The cipherStr[{}] and privateKey[{}] not be empty.", cipherStr, privateKey);
            return "";
        }
        byte[] inputByte = Base64.decodeBase64(cipherStr.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Base64.decodeBase64(privateKey);
        byte[] bytes;
        try {
            // 获取rsa密钥实例
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(SIGN_RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(SIGN_RSA);

            // 加密模式为解密，然后解密
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            bytes = cipher.doFinal(inputByte);
        } catch (Exception e) {
            log.error("RSAUtil ==> Decrypt error. ErrorMessage:{}", e.getMessage());
            return "";
        }
        return new String(bytes);
    }

    /**
     * Key内部类
     */
    public static class Key {
        public static final String PUBLIC_KEY = "aesPublicKey";

        public static final String PRIVATE_KEY = "aesPrivateKey";
    }
}
