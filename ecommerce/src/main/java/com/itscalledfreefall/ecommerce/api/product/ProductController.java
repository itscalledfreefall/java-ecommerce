package com.itscalledfreefall.ecommerce.api.product;

import com.itscalledfreefall.ecommerce.model.Product;
import com.itscalledfreefall.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService ;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
