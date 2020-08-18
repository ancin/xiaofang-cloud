package com.diandian.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author shengxiaohua
 * @Description: 金额工具类
 * @create 2020-02-13 16:00
 * @last modify by [shengxiaohua 2020-02-13 16:00]
 **/
public class MoneyUtil {
    public static int changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int money = (int)(price * 100);
        return money;
    }

    /**
     * 分转元，转换为bigDecimal在toString
     * @return
     */
    public static String changeF2Y(int price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }
}
