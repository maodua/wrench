package io.github.maodua.wrench.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


/**
 * JWT 访问令牌转换器 ：  对token的一系列操作
 */

public class TokenUtil {

    /**
     * 签发人
     */
    private final static  String iss = "sch";
    /**
     * 密钥
     */
    private final static  String secret = "secret";


    /**
     * 获取BODY中的ID
     */
    private final static String USER_ID = "user_id";
    private final static String TOKEN_LIVED = "token-lived";
    private final static String AUTH = "auth";
    /**
     * （短）令牌过期时长 2小时
     */
    public final static long SHORT_LIVED = 2 * 60 * 60;
    /**
     * （长）令牌过期时长 30天
     */
    public final static long LONG_LIVED = 30 * 24 * 60 * 60;


    public static String create(boolean longLived, String userId) {
        return create(longLived, userId, new String[0]);
    }

    /**
     * 创建token
     *
     * @param longLived 是否是长期Token
     * @param userId  用户id
     * @return token
     */
    public static String create(boolean longLived, String userId, String[] auth) {
        Algorithm algorithm = Algorithm.HMAC256(TokenUtil.secret);
        LocalDateTime time = longLived ?  LocalDateTime.now().plusSeconds(LONG_LIVED) : LocalDateTime.now().plusSeconds(SHORT_LIVED);
        // Token中存储的信息
        return JWT.create()
            .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
            .withIssuer(iss)
            .withClaim(USER_ID, userId)
            .withClaim(TOKEN_LIVED, longLived)
            .withArrayClaim(AUTH, auth)
            .sign(algorithm);
    }

    /**
     * 从token中的用户id
     *
     * @param token 令牌
     */
    public static String getUserId(String token) {
        return getJWT(token).getClaim(USER_ID).asString();
    }
    public static String getUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(USER_ID).asString();
    }

    /**
     * token 中获取权限
     *
     * @param token 令牌
     */
    public static List<String> getAuth(String token) {
        return getJWT(token).getClaim(AUTH).asList(String.class);
    }
    public static List<String> getAuth(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(AUTH).asList(String.class);
    }

    /**
     * 获取token的verifier
     */
    public static DecodedJWT getJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenUtil.secret);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(iss)
            .build();
        return verifier.verify(token);
    }
}
