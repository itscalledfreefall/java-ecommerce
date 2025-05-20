package com.itscalledfreefall.ecommerce.api.controller;

import com.itscalledfreefall.ecommerce.api.model.RegistrationBody;
import com.itscalledfreefall.ecommerce.expections.UserAlreadyExistsException;
import com.itscalledfreefall.ecommerce.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
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
}
