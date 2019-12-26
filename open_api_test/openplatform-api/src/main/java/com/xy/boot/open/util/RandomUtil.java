
package com.xy.boot.open.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class RandomUtil {

    /**
     * 长度
     */
    public static final int LENGTH_8=8;
    public static final int LENGTH_16=16;
    public static final int LENGTH_32=32;

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-+!%$#@=";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }




    public static void main(String[] args) {
        String randomString = getRandomString(64);
        log.debug(randomString);
    }
}
