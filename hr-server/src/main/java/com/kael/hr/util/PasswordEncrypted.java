package com.kael.hr.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密
 */
public class PasswordEncrypted {
    public static String encrypted(String password) {
        String passwordSalt = "8.Zse~P*$+=|Ax7X>^1iKLOt0-(*3Cw";
        String x=password+passwordSalt;
        StringBuilder result= new StringBuilder();
        try {
            MessageDigest m= MessageDigest.getInstance("MD5");
            m.update(x.getBytes(StandardCharsets.UTF_8));
            byte[] s =m.digest();
            for (byte b : s) {
                result.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
