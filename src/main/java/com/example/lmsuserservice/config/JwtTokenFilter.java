package com.example.lmsuserservice.config;

import com.example.lmsuserservice.entity.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

//if token is valid
//then access api endpoints
//or throw exception
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(Jwt.JWT_HEADER);

        if(jwt != null){
            // bearer
            jwt = jwt.substring(7);

            try {
                SecretKey key = Keys.hmacShaKeyFor(Jwt.SECRET_KEY.getBytes());

                //extract somethings from token
                //or decode
                //get claims from token
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                //get email from token
                String email = String.valueOf(claims.get("email"));

                //get authorities from token
                String authorities = String.valueOf(claims.get("authorities"));

                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                //validate email
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        auth
                );

                //set context
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            }
            catch (Exception e){
                throw new BadCredentialsException("Invalid token...");
            }

        }

        //move to next step if token valid
        filterChain.doFilter(request, response);

    }
}
