package com.coolers.housekeep.housekeep.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

    /**
     * 获取一个指定位数的随机数字符
     */
    public static String getRandomNum(int digit) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        StringBuffer stringBuffer = new StringBuffer();
        while (digit-- > 0) {
            stringBuffer.append(threadLocalRandom.nextInt(10));
        }
        return stringBuffer.toString();
    }
}
