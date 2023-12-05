package com.mlx.genshinimpactcookbook.utils;

import com.mlx.genshinimpactcookbook.domain.User;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.exception.ServiceException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Santa Antilles
 * @Date 2023/12/2-14:21
 */
public class JwtTokenUtil {
    private static final long DAY_MILLIONS = 1000 * 60 * 60;

    private static final String SIGNATURE = "mlx666";

    public static String generateEmailToken(User user){
        JwtBuilder builder = Jwts.builder();
        return builder.setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
                // 设置载荷
                .claim("email", user.getRegisterEmail())
                .claim("password", user.getEncryptedPassword())
                .setExpiration(new Date(System.currentTimeMillis()+DAY_MILLIONS))
                // 设置签名算法和签名字符串
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .compact();
    }

    public static String generateAccountToken(User user){
        JwtBuilder builder = Jwts.builder();
        return builder.setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
                // 设置载荷
                .claim("account", user.getAccount())
                .claim("password", user.getEncryptedPassword())
                .setExpiration(new Date(System.currentTimeMillis()+DAY_MILLIONS))
                // 设置签名算法和签名字符串
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .compact();
    }

    public static HashMap<String, Object> parseToken(String token){
        HashMap<String, Object> map = new HashMap<>();
        JwtParser parser = Jwts.parser();
        map.put("valid", parser.isSigned(token));
        if ((boolean)map.get("valid")) {
            Jws<Claims> parsed = null;
            try{
                parsed = parser.setSigningKey(SIGNATURE).parseClaimsJws(token);
                Claims body = parsed.getBody();
                map.put("email", body.get("email"));
                map.put("password", body.get("password"));
                map.put("account", body.get("account"));
                map.put("expired", body.getExpiration());
                map.put("signature", parsed.getSignature());
                map.put("continue", map.get("email") != null || map.get("account") != null);
                return map;
            }catch (ExpiredJwtException e){
                throw new ServiceException("令牌超时！", HttpStatus.FORBIDDEN);
            }
        }
        throw new ServiceException("令牌超时！", HttpStatus.FORBIDDEN);
    }
}
