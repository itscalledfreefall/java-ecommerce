package com.itscalledfreefall.ecommerce.service;
import com.itscalledfreefall.ecommerce.model.Product;
import com.itscalledfreefall.ecommerce.model.dao.ProductDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }



    public List<Product> getProducts(){
        return productDao.findAll();
    }
}
