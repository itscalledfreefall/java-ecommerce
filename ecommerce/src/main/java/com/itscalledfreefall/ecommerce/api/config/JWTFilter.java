package com.itscalledfreefall.ecommerce.api.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.dao.LocalUserDao;
import com.itscalledfreefall.ecommerce.service.JWTService;
import com.itscalledfreefall.ecommerce.service.MyUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
public class JWTFilter extends OncePerRequestFilter {


    JWTService jwtService;
    ApplicationContext context;
    LocalUserDao localUserDao;

    public JWTFilter(JWTService jwtService,ApplicationContext context,LocalUserDao localUserDao){
        this.jwtService = jwtService;
        this.context  =  context;
        this.localUserDao = localUserDao;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Jwt filter works");
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token =  authHeader.substring(7);
            try{
                username = jwtService.extractUserName(token);
                Optional<LocalUser> opUser =  localUserDao.findLocalUsersByUsernameIgnoreCase(username);
                if(opUser.isPresent()&& SecurityContextHolder.getContext().getAuthentication()==null){
                    LocalUser user = opUser.get();
                    System.out.println("user found"+user.getUsername());
                    UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Authentication set in SecurityContext");

                }
            }catch (JWTDecodeException ex){
                System.out.println("JWT decode error: " + ex.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;

        }
            System.out.println("Authorization Header: " + authHeader);
            System.out.println("Extracted Token: " + token);
            System.out.println("Extracted Username: " + username);
            System.out.println("SecurityContext has authentication: " + (SecurityContextHolder.getContext().getAuthentication() != null));

        }
        filterChain.doFilter(request,response);
    }
}
