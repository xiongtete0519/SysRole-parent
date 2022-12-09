package com.atguigu.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 生成JSON Web令牌的工具类
 */
public class JwtHelper {
    //token过期时间
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    //加密密钥
    private static String tokenSignKey = "123456";

    /**
     *
     * @param userId 用户id
     * @param username 用户名称
     * @return 根据用户id和用户名称生成token字符串
     */
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")    //subject
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//过期时间
                .claim("userId", userId) //载荷
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)  //设置签名
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = (String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String token = JwtHelper.createToken("1", "shy");
        System.out.println(token);
        String userId = JwtHelper.getUserId(token);
        String username = JwtHelper.getUsername(token);
        System.out.println(userId);
        System.out.println(username);
    }
}