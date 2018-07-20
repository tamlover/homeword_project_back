package com.advan.newproject.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JwtUtil {
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);


    //加密，传入一个对象和有效期
    public static  String sign(long userId, long maxAge) {
        try {
           Date date = new Date(System.currentTimeMillis() + maxAge);
            String token = JWT.create()
                    .withClaim("userId",userId)
                    .withClaim(EXP,date)
                    .sign(algorithm);
            return token;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static void verify(String jwt) {
        final JWTVerifier verifier = JWT.require(algorithm).build();
        try {

            DecodedJWT decodedJWT = verifier.verify(jwt);
//            if (decodedJWT.getClaim(EXP) != null && decodedJWT.getClaim("userId") != null) {
//
//            }
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }

}
