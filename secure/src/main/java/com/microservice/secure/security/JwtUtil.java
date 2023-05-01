package com.microservice.secure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    @Value("${jwt.expired-time}")
    private static Long expiredTime;

    @Value("${jwt.secret-key}")
    private static String secretKey;

    private static Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private static <T> T getClaimsFromToken(String token, Function<Claims, T> claimsFunction){
        Claims claims = getAllClaimsFromToken(token);
        return claimsFunction.apply(claims);
    }

    public static String getUsernameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public static Date getExpiredTime(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public static Boolean isExpired(String token){
        Date expiredDate = getExpiredTime(token);
        return expiredDate.before(new Date());
    }

    public static String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}
