package com.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "njdskdhwehw8748349021h1nnm123ih32il#24%g2k627628";
    private static final long EXPIRATION_TIME = 1000 * 60 * 10; //10 minutes

    //Creates a secure key for signing/verifying JWTs
    private Key getSignKey() {
        //applying HS256 algorithm
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //Generates a JWT for a user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //build the JWT with the claims and username
        return createToken(claims, userDetails.getUsername());
    }

    //Constructs the actual JWT string.
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + + EXPIRATION_TIME))
                //Signs the JWT with the secret key using HMAC-SHA256 algorithm
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                //Builds and serializes the JWT into a compact string
                .compact();
    }

    //Checks if a JWT is valid for a given user
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
