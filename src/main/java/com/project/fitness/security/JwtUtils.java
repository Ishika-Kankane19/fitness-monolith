package com.project.fitness.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private String jwtSecret="YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirationMs=172800000;

//    public String GetJwtFromHeader(HttpServletRequest request){
//        return "";
//    }

//    public String GenerateTokenFomUsername(String userName){
//        return Jwts.builder()
//                .subject(userName)
//                .issuedAt(new Date())// date issue jb token bana ho
//                .expiration(new Date(new Date().getTime()+jwtExpirationMs))// this step is used to u=convert the time in date because
//                // expiration ke format me jo value jaati h wo date ke format me jaati h and humne 48 hr ka tme liya h abhi development ke liye
//                //to wo milisecoonds ko date me convert kar dega
//                .signWith(key())//use to sign the token that it is authenticated
//                .compact();
//    }

    public String generateToken(String userId,String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public String getJwtHeader(HttpServletRequest request){
        String bearerToken= request.getHeader("Authorization");
        if(bearerToken!= null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


    public boolean ValidateJwtToken(String  jwtToken){
        try{
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private Key  key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserIDFromToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseSignedClaims(jwt)
                .getPayload().getSubject();// this method is used to get the username from the header isliyeh phele
        //get payload funct use kiya h phir get subject payload me hamara username hota h jo token me reheta h user name isliyeh phele payload use kar rhe h
    }

    public Claims getAllCLaim(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
    }


}
