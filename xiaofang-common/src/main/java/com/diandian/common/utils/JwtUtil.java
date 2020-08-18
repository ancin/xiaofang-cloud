package com.diandian.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 24 * 60 *60 * 1000;

    /**
     * 校验token是否正确
     * @param token
     * @param
     * @param secret
     * @return
     */
    public static boolean verify(String token, Long userId, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /***
     *
     * @param token
     * @return token中包含的用户名
     */
    public static Long getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,30天后过期
     *
     * @param
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(Long userId, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /*public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQyOTI0NzUsInVzZXJJZCI6NTZ9.3LG22_xZUwJzQ8v7ERhMLT_4RYWP03Bwq2CBwyvwm1g";
        System.out.println(getUsername(token));
    }*/
}
