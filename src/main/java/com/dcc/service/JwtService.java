package com.dcc.service;

import com.dcc.enities.Profile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
private final  String SECRET ="c56b7bd8e8616516c5419e2afa862675970003a61ff3a92074c0f172c04c3f60";


public  boolean isValid(String token, UserDetails profile){
    String userName= extractUserName(token);
    return  userName.equals(profile.getUsername()) && !isTokenExpired(token);
}
private  boolean isTokenExpired(String token){
    return  extactExpiration(token).before(new Date());

}
private Date extactExpiration(String token){
    return extractClaims(token,Claims::getExpiration);
    }
public String extractUserName(String token){
return extractClaims(token,Claims::getSubject);
    }
//return specific claim
public  <T> T extractClaims(String token, Function<Claims,T> resolver){
    Claims claims= extractAllClaims(token);
    return  resolver.apply(claims);
}

//return all claims
private Claims extractAllClaims(String token){
    return  Jwts.parser()
            .verifyWith(getSigninKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
public String generateToken(Profile profile){
    return Jwts.builder().subject(profile.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
            .signWith(getSigninKey())
            .compact();
}

    private SecretKey getSigninKey() {
    byte[] keyBytes= Decoders.BASE64URL.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
    }
}
