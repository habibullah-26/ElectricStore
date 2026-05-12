package com.habib.springbootmysqlcrud.service;

import com.habib.springbootmysqlcrud.entity.Order;
import com.habib.springbootmysqlcrud.entity.OrderItem;
import com.habib.springbootmysqlcrud.entity.Product;
import com.habib.springbootmysqlcrud.entity.User;
import com.habib.springbootmysqlcrud.entity.dtos.OrderItemDTO;
import com.habib.springbootmysqlcrud.entity.dtos.UserDTO;
import com.habib.springbootmysqlcrud.exception.UserNotFoundException;
import com.habib.springbootmysqlcrud.mappers.OrderMapper;
import com.habib.springbootmysqlcrud.repository.OrderItemRespository;
import com.habib.springbootmysqlcrud.repository.OrderRespository;
import com.habib.springbootmysqlcrud.repository.ProductRepository;
import com.habib.springbootmysqlcrud.repository.UserRepository;
import com.habib.springbootmysqlcrud.requests.orders.SaveOrderItem;
import com.habib.springbootmysqlcrud.response.ApiResponse;
import com.habib.springbootmysqlcrud.response.OrderResponse;
import com.habib.springbootmysqlcrud.response.UserOrderResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserRepository userRepo;
    private final OrderRespository orderRepo;
    private final OrderItemRespository orderItemRepo;
    private final ProductRepository productRepo;

    private OrderMapper orderMapper;

    public OrderService(UserRepository userRepo,OrderRespository repo1, OrderItemRespository orderItemRepo,
                        ProductRepository productRepo,OrderMapper orderMapper){
        this.orderRepo = repo1;
        this.orderItemRepo = orderItemRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderMapper = orderMapper;
    }


    public ApiResponse<List<OrderResponse>> createOrder(Long userId, List<SaveOrderItem> items) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double tAmount = 0.0;
        for (SaveOrderItem item : items) {
            OrderItem item1 = new OrderItem();
            item1.setOrder(order);
            Product productOptional = productRepo.findById(item.getProductId()).orElseThrow(()-> new UserNotFoundException("User not Found"));
            item1.setProduct(productOptional);
            item1.setQuantity(Integer.parseInt(item.getQuantity()+""));
            tAmount += item1.getQuantity() * item1.getProduct().getPrice();
            orderItems.add(item1);
        }
        order.setItems(orderItems);
        order.setTotal_amount(tAmount);
        order.setCreation_date(LocalDateTime.now().toString());
        Order orderItem1 = orderRepo.save(order);
        OrderResponse orderResponse1 = orderMapper.mapToOrderResponse(orderItem1);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderResponseList.add(orderResponse1);

        return new ApiResponse<>(200, "Order Placed Successfully" ,orderResponseList);
    }

    public ApiResponse<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> responseList = orderRepo.findAll()
                .stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();

//        List<Order> orderList = orderRepo.findAll();
//        List<OrderResponse> orderListNew = new ArrayList<>();
//        for (Order item : orderList){
//            OrderResponse orderResponse = new OrderResponse();
//            orderResponse.setId(item.getId());
//            orderResponse.setStatus(item.getStatus());
//            orderResponse.setUser(new UserDTO(item.getUser().getId(),item.getUser().getName(),item.getUser().getEmail()));
//           List<OrderItemDTO> itemList = new ArrayList<>();
//           for(OrderItem orderItem : item.getItems()){
//               OrderItemDTO orderItemDTO = new OrderItemDTO();
//               orderItemDTO.setId(orderItem.getId());
//               orderItemDTO.setQuantity((long)orderItem.getQuantity());
//               orderItemDTO.setProduct(orderItem.getProduct());
//               itemList.add(orderItemDTO);
//           }
//            orderResponse.setOrderItemDTOList(itemList);
//            orderListNew.add(orderResponse);
//        }
        return new ApiResponse<>(200, "success" ,responseList);
    }

    public ApiResponse<List<OrderResponse>> getOrderById(Long orderId){
        List<OrderResponse> responseList = orderRepo.findById(orderId)
                .stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
        return new ApiResponse<>(200, "success" ,responseList);
    }

    public ApiResponse<List<OrderResponse>> getOrderByUserId(Long userID){
        List<OrderResponse> responseList = orderRepo.findByUserId(userID)
                .stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
        return new ApiResponse<>(200, "success" ,responseList);
    }


    public ApiResponse<List<UserOrderResponse>> getUserOrderNumbers(Long userID){
        User user = userRepo.findById(userID).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        Long count = orderRepo.countByUserId(userID);
        UserOrderResponse userOrderResponse = new UserOrderResponse();
        userOrderResponse.setId(user.getId());
        userOrderResponse.setName(user.getName());
        userOrderResponse.setEmail(user.getEmail());
        userOrderResponse.setTotalOrders(count);
        List<UserOrderResponse> list = new ArrayList<>();
        list.add(userOrderResponse);

        return new ApiResponse<>(200, "success" ,list);
    }

    public ApiResponse updateOrderStatus(Long orderId, String status){

        Order order = orderRepo.findById(orderId).orElseThrow(()-> new UserNotFoundException("Order not found"));
        order.setStatus(status);
        Order order1 = orderRepo.save(order);

        return new ApiResponse<>(200, "success" ,null);
    }


}
