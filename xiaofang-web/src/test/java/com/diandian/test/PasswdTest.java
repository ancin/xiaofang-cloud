package com.diandian.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName PasswdTest
 * @description:
 * @author: ancin
 * @create: 2020-08-20 13:38
 * @Version 1.0
 **/
public class PasswdTest {

    public static void main(String[] args){
        String hashName = "md5";
        String pswd = "111111";
        ByteSource salt = ByteSource.Util.bytes("zh");
        Object simplePwd = new SimpleHash(hashName,pswd,salt,2);
        System.out.println(simplePwd);
    }
}
