package com.habib.springbootmysqlcrud.mappers;

import com.habib.springbootmysqlcrud.entity.Order;
import com.habib.springbootmysqlcrud.entity.OrderItem;
import com.habib.springbootmysqlcrud.entity.Product;
import com.habib.springbootmysqlcrud.entity.dtos.OrderItemDTO;
import com.habib.springbootmysqlcrud.entity.dtos.ProductDTO;
import com.habib.springbootmysqlcrud.entity.dtos.UserDTO;
import com.habib.springbootmysqlcrud.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setStatus(order.getStatus());

        response.setUser(
                new UserDTO(
                        order.getUser().getId(),
                        order.getUser().getName(),
                        order.getUser().getEmail()
                )
        );

        List<OrderItemDTO> itemList = order.getItems()
                .stream()
                .map(this::mapToOrderItemDTO)
                .toList();
        Double totalAmount = itemList.stream()
                .mapToDouble(OrderItemDTO::getAmount)
                .sum();
        response.setTotalAmount(totalAmount);

        response.setOrderItemDTOList(itemList);

        return response;
    }

    private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {

        OrderItemDTO dto = new OrderItemDTO();

        dto.setId(orderItem.getId());
        dto.setQuantity((long) orderItem.getQuantity());
        dto.setProduct(mapToProductDTO(orderItem.getProduct()));
        dto.setAmount(dto.getProduct().getPrice() * dto.getQuantity());

        return dto;
    }

    private ProductDTO mapToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return  productDTO;
    }
}
