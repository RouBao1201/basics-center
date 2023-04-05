package com.roubao.common.security.bean;

import cn.hutool.core.util.StrUtil;
import com.roubao.common.security.properties.Md5Properties;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@Slf4j
public class MD5Util {
    private static final String MESSAGE_DIGEST = "MD5";

    private final Md5Properties md5Properties;

    public MD5Util(Md5Properties md5Properties) {
        this.md5Properties = md5Properties;
    }

    /**
     * 加密
     *
     * @param str 原密码
     * @return 密码密文
     */
    public String encrypt(String str) {
        if (StrUtil.isEmpty(str)) {
            log.error("MD5Util ==> The encryption password cannot be empty.");
            return "";
        }

        // 使用指定的算法名称创建消息摘要。可选项有 MD5  SHA-1  SHA-256
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(MESSAGE_DIGEST);
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5Util ==> Encrypt is error[NoSuchAlgorithmException]. return default cipher is empty. ErrorMessage:{}", e.getMessage());
            return "";
        }

        // 使用指定的字节更新摘要（原密码+盐值）
        md.update((str + this.md5Properties.getSalt()).getBytes());

        //  产生用于生成的哈希值的字节数组。
        byte[] md5Bytes = md.digest();
        return byteArrayToHex(md5Bytes);
    }

    /**
     * 校验密码是否正确
     *
     * @param oldCipher 旧密码密文
     * @param newStr    新密码原文
     * @return 校验结果
     */
    public boolean verify(String oldCipher, String newStr) {
        String newEncrypt = encrypt(newStr);
        if (StrUtil.isEmpty(newEncrypt) || StrUtil.isEmpty(oldCipher)) {
            return false;
        }
        return newEncrypt.equals(oldCipher);
    }

    private String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'b', 'd', 'e', 'f'};

        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];

        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
