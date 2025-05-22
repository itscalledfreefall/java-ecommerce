package com.itscalledfreefall.ecommerce.model.dao;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserDao extends ListCrudRepository<LocalUser,Long> {
    Optional<LocalUser>  findLocalUsersByUsernameIgnoreCase(String username);
    LocalUser findByEmailIgnoreCase(String email);
}
