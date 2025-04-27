package com.project.doctorya.auth;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static final String jwtSecret = "VHhTS0o4cU5kMm5rSlBtZUVpc3M2MkdJYjRZV0pDMVdwVzN4S1c1Uw=="; // Ponlo en application.properties idealmente
    private static final long jwtExpirationMs = 7200000; // 2 horas

    public static String generateToken(String identification) {
        return Jwts.builder()
                .setSubject(identification)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getIdentificationFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("JWT error: " + e.getMessage());
        }
        return false;
    }

    public static long getJwtexpirationms() {
        return jwtExpirationMs;
    }
}
