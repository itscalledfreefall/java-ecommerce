package com.itscalledfreefall.ecommerce.api.order;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.WebOrder;
import com.itscalledfreefall.ecommerce.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

private OrderService service;

public OrderController(OrderService service){
    this.service = service;
}
@GetMapping
    public List<WebOrder> getWebOrders(@AuthenticationPrincipal LocalUser user){
    return service.getOrders(user);
}

}
