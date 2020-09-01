package com.diandian.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 *
 * 12bf5aa82887cc84e7a20adee1f3ece5   111111
 * fd41c0533ab4bf7d68377b8c52e25f8d   123456
 * 11257df60bd6a43eb0c1b3e696a70399   admin123
 *
 * @ClassName PasswdTest
 * @description:
 * @author: ancin
 * @create: 2020-08-20 13:38
 * @Version 1.0
 **/
public class PasswdTest {

    public static void main(String[] args){
        String hashName = "md5";
        String pswd = "admin123";
        ByteSource salt = ByteSource.Util.bytes("zh");
        Object simplePwd = new SimpleHash(hashName,pswd,salt,2);
        System.out.println(simplePwd);
    }
}
