package com.tenpo.challenge.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JWTTokenServiceImpl {
  private static final Map<String, List<String>> JWTTokenBlacklistMap = new ConcurrentHashMap<>();

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.jwtExpiration}")
  private long tokenExpiration;

  public String getJWTToken(Authentication authentication) {
    UserJpaEntity user = (UserJpaEntity) authentication.getPrincipal();
    long start = System.currentTimeMillis();
    return JWT.create()
        .withSubject(user.getUsername())
        .withIssuedAt(new Date(start))
        .withExpiresAt(new Date(start + (this.tokenExpiration * 60 * 1000)))
        .withClaim(
            "roles",
            user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(Algorithm.HMAC256(this.secretKey.getBytes()));
  }

  public String getUsernameFromToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    String username = this.getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && this.isValidToken(username, token));
  }

  private boolean isValidToken(String username, String token) {
    boolean isBlacklistedToken =
        JWTTokenBlacklistMap.containsKey(username)
            && JWTTokenBlacklistMap.get(username).contains(token);

    return !this.isTokenExpired(token) && !isBlacklistedToken;
  }

  private boolean isTokenExpired(String token) {
    DecodedJWT jwt = JWT.decode(token);
    return jwt.getExpiresAt().before(new Date());
  }

  public void invalidateUserToken(String token) {
    String username = this.getUsernameFromToken(token);

    JWTTokenBlacklistMap.putIfAbsent(username, new ArrayList<>());

    JWTTokenBlacklistMap.computeIfPresent(
        username,
        (key, currentList) -> {
          currentList.add(token);
          return currentList;
        });
  }
}
