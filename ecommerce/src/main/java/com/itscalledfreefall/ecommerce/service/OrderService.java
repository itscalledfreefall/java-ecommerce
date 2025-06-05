package com.itscalledfreefall.ecommerce.service;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.WebOrder;
import com.itscalledfreefall.ecommerce.model.dao.WebOrderDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private WebOrderDao webOrderDao;

    public OrderService(WebOrderDao webOrderDao) {
        this.webOrderDao = webOrderDao;
    }



    public List<WebOrder> getOrders(LocalUser user){
        return webOrderDao.findByUser(user);
    }
}



