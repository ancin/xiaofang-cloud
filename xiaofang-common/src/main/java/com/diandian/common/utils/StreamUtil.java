package com.diandian.common.utils;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2020-03-29 21:37
 * @last modify by [shengxiaohua 2020-03-29 21:37]
 **/
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil
{
    public static String read(InputStream is, String charset)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[512];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return new String(baos.toByteArray(), 0, baos.size(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static byte[] read2bytes(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[512];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }return null;
    }
}