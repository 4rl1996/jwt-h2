package com.example.jwt.service.impl;

import com.example.jwt.dao.entity.UserEntity;
import com.example.jwt.exception.TokenProcessingException;
import com.example.jwt.exception.UserNotFoundException;
import com.example.jwt.service.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    private final SecretKey secretKey;

    @Value("${token.alg}")
    private String algorithm; // условный white-list алгоритмов шифрования

    public TokenServiceImpl(@Value("${token.secret}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    @Override
    public String generateToken(UserEntity user) {

        final Date accessExpiration = Date.from(
                LocalDateTime.now(Clock.systemUTC()).plusMinutes(5).toInstant(ZoneOffset.UTC)); // время жизни токена

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(accessExpiration)
                .signWith(secretKey)
                .claim("roles", user.getRoles())
                .compact();
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, secretKey);
    }

    private boolean validateToken(String token, Key secret) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getHeader().getAlgorithm().equals(algorithm); // проверяем, входит ли алгоритм шифроания в условный white-list
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
            throw new TokenProcessingException(expEx.getMessage());
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
            throw new TokenProcessingException(unsEx.getMessage());
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
            throw new TokenProcessingException(mjEx.getMessage());
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
            throw new TokenProcessingException(sEx.getMessage());
        } catch (Exception e) {
            log.error("invalid token", e);
            throw new TokenProcessingException(e.getMessage());
        }
    }

    public Claims getClaims(String token) {
        return getClaims(token, secretKey);
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
