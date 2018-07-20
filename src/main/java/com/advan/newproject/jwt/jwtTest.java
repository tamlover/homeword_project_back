package com.advan.newproject.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Header;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class jwtTest {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoxMjN9.7Diqx9FPPuaw9ESwkZOHL2BARjqQz00qrHYOm0lKcgQ";
    String token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
    String tokenn = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    public String test() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    @Test
    public void test2 () {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
//            String tt2 = JWT.create()
//                    .withClaim("name", 123)
//                    .sign(algorithm);
//            System.out.println(tt2);
//            String tt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIwrtUzI1NiJ9.yJuYW1lIjoxMjN9.7Diqx9FPPuaw9ESwkZOHL2BARjqQz00qrHYOm0lKcgQ";
//            String tt ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("name", 345346)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void test3 () {
        final JWTParser converter = new JWTParser();
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token3 = JWT.create()
                .withClaim("name", 123)
                .withArrayClaim("array", new Integer[]{1, 2, 3})
                .sign(algorithm);
        System.out.println("____________" + token3);
        try {
            DecodedJWT jwt = JWT.decode(tokenn);
            String headerJson = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getToken()));
            String heaJson = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getHeader()));
            String payJson = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getPayload()));
            String sigJson = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getSignature()));
//            System.out.println(headerJson);
//            System.out.println(heaJson);
//            System.out.println(payJson);
//            System.out.println(sigJson);
            System.out.println(jwt.getSignature());

            Header header = converter.parseHeader(headerJson);
            System.out.println(header.getAlgorithm());
        } catch (JWTDecodeException exception){
            //Invalid token
        }


    }
}
