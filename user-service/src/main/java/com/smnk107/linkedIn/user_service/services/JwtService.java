package com.smnk107.linkedIn.user_service.services;

import com.smnk107.linkedIn.user_service.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtKey;

    private SecretKey createSecretKey(String skey)
    {
        return Keys.hmacShaKeyFor(skey.getBytes(StandardCharsets.UTF_8));
    }
    public String createAccessToken(User user)
    {
        return Jwts.builder()
                .subject(Long.toString(user.getId()))
                .claim("email",user.getEmail())
                //.claim("roles", Set.of("USER","ADMIN"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+(10000*60)))
                .signWith(createSecretKey(jwtKey))
                .compact();//Compiles all the information and generates a compact, URL-safe JWT string.
    }

//    public String createRefreshToken(User user)
//    {
//        return Jwts.builder()
//                .subject(Long.toString(user.getId()))
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis()+(1000L *60*60*24*30*6)))
//                .signWith(createSecretKey(jwtKey))
//                .compact();//Compiles all the information and generates a compact, URL-safe JWT string.
//    }

    public Long getUserIdFromToken(String token)
    {

        Claims claims = Jwts.parser()
                .verifyWith(createSecretKey(jwtKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        System.out.println(Integer.valueOf(claims.getSubject()));
        return Long.valueOf(claims.getSubject());
    }


}
