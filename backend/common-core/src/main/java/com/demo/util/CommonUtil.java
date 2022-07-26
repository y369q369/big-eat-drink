package com.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @Author gs
 * @Date 2021-10-28 10:39
 * @Description 公用工具类
 */
@Slf4j
public class CommonUtil {

    /**
     * 获取UUID
     */
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }


    /**
     * 原理是从汉字区位码找到汉字。在汉字区位码中分高位与底位， 且其中简体又有繁体。
     * 位数越前生成的汉字繁体的机率越大。
     * 所以在本例中高位从176取，底位从161取， 去掉大部分的繁体和生僻字。但仍然会有！！
     */
    public static char getRandomChar() {
        SecureRandom random = new SecureRandom();
        int hightPos = (176 + Math.abs(random.nextInt(39)));
        int lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            String str = new String(b,"GBK");
            return str.charAt(0);
        } catch (UnsupportedEncodingException e) {
            log.error("【汉字随机生成器】 编码异常", e);
            // 异常时返回空格
            return 32;
        }
    }

    /**
     * 获取随机小数点的随机值
     * @param max 整数部分最大范围
     * @param digit 保留小数点数
     */
    public static double randomDouble(int max, int digit) {
        // 整数部分
        BigDecimal integer = new BigDecimal(new SecureRandom().nextInt(max));
        // 小数部分
        BigDecimal decimal = BigDecimal.valueOf(new SecureRandom().nextDouble()).setScale(digit, RoundingMode.HALF_UP);
        // 值
        BigDecimal value = integer.add(decimal);
        return value.doubleValue();
    }

    /**
     * 获取百分比
     * @param num1 除数
     * @param num2 被除数
     * @param length 保留小数位数
     * @param roundingMode 小数点保留方式，默认四舍五入 ROUND_UP, ROUND_DOWN, ROUND_CEILING, ROUND_FLOOR, ROUND_HALF_UP, ROUND_HALF_DOWN, ROUND_HALF_EVEN, ROUND_UNNECESSARY
     * @return 百分比，被除数为0时，默认返回100
     */
    public static double percentage(long num1, long num2, int length, int... roundingMode) {
        int mode = roundingMode.length > 0 ? roundingMode[0] : BigDecimal.ROUND_HALF_UP;
        BigDecimal divisor = BigDecimal.valueOf(num1);
        BigDecimal dividend = BigDecimal.valueOf(num2);
        if (dividend.equals(BigDecimal.ZERO)) {
            return 100;
        } else {
            BigDecimal percent = divisor.multiply(BigDecimal.valueOf(100)).divide(dividend, length, mode);
            return percent.doubleValue();
        }
    }

    public static void main(String[] args) {
        System.out.println(percentage(2, 3, 3, BigDecimal.ROUND_DOWN));
    }

}
