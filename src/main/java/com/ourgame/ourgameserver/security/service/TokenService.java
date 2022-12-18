package com.ourgame.ourgameserver.security.service;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;


@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

     public String generateToken(Authentication authentication) {
         Instant now = Instant.now();
         String scope = authentication.getAuthorities().stream()
                 .map(GrantedAuthority::getAuthority)
                 .collect(Collectors.joining(" "));
         JwtClaimsSet claims = JwtClaimsSet.builder()
                 .claim("scope", scope)
                 .issuer("self")
                 .issuedAt(now)
                 .expiresAt(now.plus(24, ChronoUnit.HOURS))
                 .subject(authentication.getName())
                 .build();
         return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
     }
}
