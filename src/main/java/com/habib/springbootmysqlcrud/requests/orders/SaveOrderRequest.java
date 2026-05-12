package com.habib.springbootmysqlcrud.requests.orders;

import java.util.List;

public class SaveOrderRequest {

    private Long userId;
    private List<SaveOrderItem> items;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SaveOrderItem> getItems() {
        return items;
    }

    public void setItems(List<SaveOrderItem> items) {
        this.items = items;
    }
}
