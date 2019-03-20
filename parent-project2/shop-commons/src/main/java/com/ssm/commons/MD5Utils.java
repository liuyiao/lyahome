package com.ssm.commons;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
    //干扰数据 盐 防破解
    private static final String SALT = "1231~!@##%$RT!@#@/.%#$@";
    //散列算法类型为MD5
    private static final String algorithmName = "md5";
    //hash的次数
    private static final int HASH_ITERATIONS = 1024;

    public static String encrypt(String pswd) {

        /**
         *形参1：加密的方式  md5
         * 形参2：要加密的原始字符串
         * 形参3：加密盐值
         * 形参4：加密的次数
         *
         */
        String newPassword = new SimpleHash(algorithmName, pswd, ByteSource.Util.bytes(SALT),HASH_ITERATIONS).toString();
        return newPassword;
    }
    public static void main(String[] args) {
        System.out.println("加密后String"+MD5Utils.encrypt("123"));
    }
}
