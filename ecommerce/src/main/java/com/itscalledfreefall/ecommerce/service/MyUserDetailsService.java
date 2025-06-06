package com.itscalledfreefall.ecommerce.service;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.LocalUserPrincipal;
import com.itscalledfreefall.ecommerce.model.dao.LocalUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {

    private LocalUserDao localUserDao;

    public MyUserDetailsService(LocalUserDao localUserDao){
        this.localUserDao = localUserDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser user = localUserDao.findLocalUsersByUsernameIgnoreCase(username).get();
        if(user == null){

            throw new UsernameNotFoundException("User not found");
        }
        return new LocalUserPrincipal(user);

    }
}
