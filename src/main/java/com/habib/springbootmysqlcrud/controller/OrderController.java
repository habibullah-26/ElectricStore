package com.habib.springbootmysqlcrud.controller;
import com.habib.springbootmysqlcrud.requests.orders.SaveOrderRequest;
import com.habib.springbootmysqlcrud.requests.orders.UpdateStatus;
import com.habib.springbootmysqlcrud.response.ApiResponse;
import com.habib.springbootmysqlcrud.response.OrderResponse;
import com.habib.springbootmysqlcrud.response.UserOrderResponse;
import com.habib.springbootmysqlcrud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ApiResponse<List<OrderResponse>> createOrder(@RequestBody SaveOrderRequest order) {
        return orderService.createOrder(order.getUserId(),order.getItems());
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ApiResponse<List<OrderResponse>> getOrdersById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<List<OrderResponse>> getOrdersByUserId(@PathVariable Long id){
        return orderService.getOrderByUserId(id);
    }

    @GetMapping("user/count/{id}")
    public ApiResponse<List<UserOrderResponse>> getUserOrderNumbers(@PathVariable Long id){
        return orderService.getUserOrderNumbers(id);
    }

    @PostMapping("updateStatus")
    public ApiResponse updateOrderStatus(@RequestBody UpdateStatus updateStatus){
        return orderService.updateOrderStatus(updateStatus.getOrderId(),updateStatus.getStatus());
    }


}