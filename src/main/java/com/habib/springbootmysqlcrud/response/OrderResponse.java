package com.habib.springbootmysqlcrud.response;

import com.habib.springbootmysqlcrud.entity.dtos.OrderItemDTO;
import com.habib.springbootmysqlcrud.entity.dtos.UserDTO;

import java.util.List;

public class OrderResponse {

    private Long id;
    private String status;
    private UserDTO user;
    private Double totalAmount;
    private List<OrderItemDTO> Items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<OrderItemDTO> getOrderItemDTOList() {
        return Items;
    }

    public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
        this.Items = orderItemDTOList;
    }
}
