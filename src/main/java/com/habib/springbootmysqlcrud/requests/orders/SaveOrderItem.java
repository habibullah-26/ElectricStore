package com.habib.springbootmysqlcrud.requests.orders;

public class SaveOrderItem {
    private Long productId;
    private Long quantity;

    public SaveOrderItem(){

    }

    public SaveOrderItem(Long productId,Long quantity){
        this.productId = productId;
        this.quantity = quantity;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
