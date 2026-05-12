package com.habib.springbootmysqlcrud.response;

public class UserOrderResponse {


        private Long id;
        private String name;
        private String email;
        private Long totalOrders;

        public  UserOrderResponse(){

        }
    public UserOrderResponse(Long id, String name, String email, Long totalOrders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.totalOrders = totalOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }
}
