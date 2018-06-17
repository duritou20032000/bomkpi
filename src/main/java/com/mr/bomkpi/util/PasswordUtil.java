package com.mr.bomkpi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;

public class PasswordUtil implements PasswordEncoder{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SPLITTER = ":";
    private static final String PREFIX = "E";
    public static final String MASKER = "********";

    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            return new BigInteger(1,array).toString(16);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 error");
        }
    }

    private static String doEncrypt(String input, String salt) {
        return MD5(salt + MD5(input));
    }

    public static boolean validatePasswd(String input, String encryptedPasswd){
        if (input != null && encryptedPasswd != null){
            if (encryptedPasswd.indexOf(PREFIX + SPLITTER) == 0){
                String[] group = encryptedPasswd.split(SPLITTER);
                return group.length == 3 && doEncrypt(input, group[1]).equals(group[2]);
            }else{
                return input.equals(encryptedPasswd);
            }
        }
        return false;
    }

    public static String encryptPasswd(String input) {
        String salt = String.valueOf(System.currentTimeMillis());
        return PREFIX + SPLITTER + salt + SPLITTER + doEncrypt(input, salt);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encryptPasswd(String.valueOf(rawPassword));
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        logger.info("raw ===================="+String.valueOf(rawPassword)+"encodePassword+++++++++++"+encodedPassword);
        return validatePasswd(String.valueOf(rawPassword),encodedPassword);
    }
}
