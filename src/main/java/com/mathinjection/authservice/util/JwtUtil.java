package com.mathinjection.authservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    private String secret_key = "SECRET";
    private Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
    private JWTVerifier verifier = JWT.require(algorithm).build();

    public Map<String, String> generateTokens(String subject, Collection<? extends GrantedAuthority> authorities) {
        String access_token = JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                .sign(algorithm);

        return new HashMap<>() {{
            put("access_token", access_token);
            put("refresh_token", refresh_token);
        }};


    }

    public String getSubject(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            return username;
        } catch (Exception e) {
            throw new JWTVerificationException("Invalid token");
        }
    }

    public Collection<GrantedAuthority> getAuthorities(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        String[] authorities = decodedJWT.getClaim("authorities").asArray(String.class);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Arrays.stream(authorities).forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority)));

        return grantedAuthorities;
    }

}
