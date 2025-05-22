package com.itscalledfreefall.ecommerce.model.dao;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;


import java.util.List;

public interface WebOrderDao extends ListCrudRepository<WebOrder,Long> {
    List<WebOrder> findByUser(LocalUser user);
}
