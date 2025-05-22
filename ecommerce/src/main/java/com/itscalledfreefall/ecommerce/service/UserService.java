package com.itscalledfreefall.ecommerce.service;

import com.itscalledfreefall.ecommerce.api.model.RegistrationBody;
import com.itscalledfreefall.ecommerce.expections.UserAlreadyExistsException;
import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.dao.LocalUserDao;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private LocalUserDao localUserDao;

    private JWTService jwtService;


     private AuthenticationManager authManager;


    private EncryptionService encryptionService;

    private EncryptionService service;

    public UserService(LocalUserDao localUserDao, EncryptionService service, EncryptionService encryptionService, AuthenticationManager authManager, JWTService jwtService) {
        this.localUserDao = localUserDao;
        this.service = service;
        this.encryptionService = encryptionService;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public LocalUser register(RegistrationBody registrationBody)throws UserAlreadyExistsException {
        if(localUserDao.findLocalUsersByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        return localUserDao.save(user);
    }
    public String verify(LocalUser user){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return "fail";
        }


    }






}
