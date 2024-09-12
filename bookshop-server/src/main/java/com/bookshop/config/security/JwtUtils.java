package com.bookshop.config.security;

import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// Giai phap : https://viblo.asia/p/lam-sao-bao-mat-ung-dung-spring-voi-spring-security-va-jwt-yZjJYKXbVOE#_43---jwtutils-9

@Component
@Slf4j
public class JwtUtils {

    @Value("${bookshop.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bookshop.app.jwtExpirationMs}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Invalid JWT expired {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Invalid JWT unsupported {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Invalid JWT empty {}", e.getMessage());
        }

        return false;
    }

}