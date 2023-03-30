package io.github.maodua.wrench.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenUtilTest{
    Algorithm algorithm = Algorithm.HMAC256("secret");
    private final static  String iss = "sch";

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

    @Test
    public void create(){
        String[] strings = {};
        LocalDateTime time = LocalDateTime.now();
        // Token中存储的信息
        String token = JWT.create()
            .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
            .withIssuer(iss)
            .withClaim(USER_ID, "aaaa")
            .withClaim(TOKEN_LIVED, false)
            .withArrayClaim(AUTH, strings)
            .sign(algorithm);
        System.out.println(token);
    }
    @Test
    public void xxx(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbiI6IntcInVzZXJJZFwiOlwiMVwiLFwibG9naW5DbGllbnRcIjpcIkJBQ0tcIixcImxvZ2luVG9rZW5cIjpcIjQyOTdmNDRiMTM5NTUyMzUyNDViMjQ5NzM5OWQ3YTkzXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIixcInJlbWVtYmVyTWVcIjpmYWxzZX0ifQ.wjp2JYxutwzzwyqfybjFBJg_RR5Fqop5RBl5FtUnxGY";

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        String userId = jwt.getClaim(USER_ID).asString();
        System.out.println(userId);
    }
}
