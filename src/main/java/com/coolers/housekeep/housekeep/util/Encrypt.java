package com.coolers.housekeep.housekeep.util;

import com.coolers.housekeep.housekeep.constant.EncryptConst;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    /**
     * MD5加密算法，不可再次查看加密前文本，仅适用于加密和校验
     */
    public static String encryptByMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(EncryptConst.ENCRYPT_ALGORITHM_MD5);
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer stb = new StringBuffer();
        for (byte bt : digest) {
            stb.append(String.format(EncryptConst.ENCODE_FORMAT_HEXADECIMAL, bt));
        }
        return stb.toString();
    }

}
