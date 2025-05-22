package com.itscalledfreefall.ecommerce.model.dao;

import com.itscalledfreefall.ecommerce.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ListCrudRepository<Product,Long> {

}
