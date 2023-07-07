package com.cg.LeaveManagement.jwt.util;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.cg.LeaveManagement.Exception.JwtTokenMalformedException;
import com.cg.LeaveManagement.Exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JWTUtils {
	static String jwtSecret = "Hari@1309";
	public static Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		}
		catch (Exception e) {
          throw new JwtException("Invalid token");
			}
       }
     public static String generateToken(String id) {
         Claims claims =Jwts.claims().setSubject(id);
        long nowMillis=System.currentTimeMillis();
             long expMillis=60*60*1000;
         Date expDate= new Date(System.currentTimeMillis() + expMillis);
         return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(expDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
     }
    public static void validateToken(HttpServletRequest request) throws JwtTokenMalformedException, JwtTokenMissingException {
        String token =request.getHeader("Authorization");
      try {
       Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
       }catch (MalformedJwtException ex){
            throw new JwtTokenMalformedException("Invalid JWT token");
       }catch(UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
       }catch(IllegalArgumentException ex) {
            throw new JwtTokenMissingException("Jwt claims string is empty");
       }
     }
}
