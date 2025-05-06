package com.project.doctorya.config;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static final String jwtSecret = "VHhTS0o4cU5kMm5rSlBtZUVpc3M2MkdJYjRZV0pDMVdwVzN4S1c1Uw==";
    private static final long jwtExpirationMs = 7200000; // 2 horas

    public static String generateToken(String identification, String role) {
        SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setSubject(identification)
                .claim("roles", List.of(role)) // debe ser una lista
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String getJwtSecretKey() {
        return jwtSecret;
    }
}
