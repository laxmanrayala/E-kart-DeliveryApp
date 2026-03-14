package com.projectla.deliveryapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "supersecretkeysupersecretkeysupersecretkey";

    private final long EXPIRATION = 86400000; // 1 day

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(Long userId, String role) {

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role) // 🔑 add role
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    public Long extractUserId(String token) {

        Claims claims = getClaims(token);

        return Long.parseLong(claims.getSubject());
    }

    public String extractRole(String token) {

        Claims claims = getClaims(token);

        return claims.get("role", String.class);
    }

    private Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}