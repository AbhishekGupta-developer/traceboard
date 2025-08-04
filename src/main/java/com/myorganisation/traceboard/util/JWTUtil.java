package com.myorganisation.traceboard.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    public JWTUtil(@Value("${jwt.secret}") String jwtSecret) {
        SECRET = jwtSecret;
        KEY=Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    private final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private final String SECRET;
    private final SecretKey KEY;
    private final long EXPIRATION_TIME = 1000 * 60 * 5; //Valid for 5 mins

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        Claims body = getClaims(token);

        return body.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        logger.info("Inside JWTUtil -> validateToken | username: {} | UserDetails: {} | Token: {}", username, userDetails, token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
