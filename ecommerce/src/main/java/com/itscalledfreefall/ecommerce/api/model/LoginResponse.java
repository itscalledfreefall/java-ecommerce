package com.itscalledfreefall.ecommerce.api.model;

public class LoginResponse {
    private String jwt;

    public String getJWT(){
        return jwt;
    }
    public void setJWT(String jwt){
        this.jwt = jwt;
    }
}
