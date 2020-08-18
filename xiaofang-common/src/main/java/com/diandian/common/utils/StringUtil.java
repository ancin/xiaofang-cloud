package com.diandian.common.utils;

import java.text.DecimalFormat;

/**
 * @author shengxiaohua
 * @Description: 字符串util
 * @create 2020-03-29 13:04
 * @last modify by [shengxiaohua 2020-03-29 13:04]
 **/
public class StringUtil {

    public static String getRandomName(int counts){
        String randomcode = "";
        String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();

        for (int j=0;j<counts ;j++ ){
            char c = m[(int)(Math.random()*26)];
            randomcode = randomcode + c;
        }
        return randomcode+"-"+System.currentTimeMillis();
    }

    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}
