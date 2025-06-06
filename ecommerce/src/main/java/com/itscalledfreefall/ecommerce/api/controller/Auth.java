package com.itscalledfreefall.ecommerce.api.controller;

import com.itscalledfreefall.ecommerce.api.model.LoginBody;
import com.itscalledfreefall.ecommerce.api.model.LoginResponse;
import com.itscalledfreefall.ecommerce.api.model.RegistrationBody;
import com.itscalledfreefall.ecommerce.expections.UserAlreadyExistsException;
import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.LocalUserPrincipal;
import com.itscalledfreefall.ecommerce.service.MyUserDetailsService;
import com.itscalledfreefall.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Auth {


    public Auth(UserService service){
        this.service =  service;
    }
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            service.register(registrationBody);
            return ResponseEntity.ok().build();
        }
        catch (UserAlreadyExistsException e ){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody  loginBody){
    String jwt = service.login(loginBody);
    if(jwt == null){
        System.out.println("JWT is null");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
    else{
        LoginResponse response =  new LoginResponse();
        response.setJWT(jwt);
        return ResponseEntity.ok(response);
    }
        }
    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUserPrincipal localUserPrincipal){
        if(localUserPrincipal==null){
            return null;
        }
        return localUserPrincipal.getLocalUser();
    }
}
