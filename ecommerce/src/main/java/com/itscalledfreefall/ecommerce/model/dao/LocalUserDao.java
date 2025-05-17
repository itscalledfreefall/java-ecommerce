package com.itscalledfreefall.ecommerce.model.dao;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalUserDao extends JpaRepository<LocalUser,Integer> {
    LocalUser findLocalUsersByUsernameIgnoreCase(String username);
}
