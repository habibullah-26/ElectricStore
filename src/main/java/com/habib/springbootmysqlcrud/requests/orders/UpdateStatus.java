package com.habib.springbootmysqlcrud.requests.orders;

public class UpdateStatus {
    Long orderId;
    String status;

    public  UpdateStatus(){

    }
    public UpdateStatus(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
