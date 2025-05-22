package com.itscalledfreefall.ecommerce.service;

import com.itscalledfreefall.ecommerce.model.LocalUser;
import com.itscalledfreefall.ecommerce.model.WebOrder;
import com.itscalledfreefall.ecommerce.model.dao.WebOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private WebOrderDao webOrderDao;

    public List<WebOrder> getOrders(LocalUser user){
        return webOrderDao.findByUser(user);
    }
}



