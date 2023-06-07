package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.OrderNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.OrderDetailsPayload;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.OrderPayload;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServices {

    private final OrdersRepository ordersRepository;
    @Autowired
    private ItemsServices itemsServices;
    @Autowired
    private TablesServices tablesServices;

    @Autowired
    public OrdersServices(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders addOrder(OrderPayload orderPayload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsPayload orderDetailPayload : orderPayload.getOrderDetail()) {
            OrderDetails orderDetail = OrderDetails.builder()
                    .item(itemsServices.findItemById(orderDetailPayload.getItemId()))
                    .quantity(orderDetailPayload.getQuantity())
                    .build();
            orderDetails.add(orderDetail);
        }
        LocalDateTime now = LocalDateTime.now();
        Orders newOrder = Orders.builder()
                .id(orderPayload.getId())
                .userName(username)
                .table(tablesServices.findTableById(orderPayload.getTableID()))
                .total(orderPayload.getTotal())
                .note(orderPayload.getNote())
                .orderDetail(orderDetails)
                .isDeleted(false)
                .creationDate(now)
                .build();
        Orders savedOrder = ordersRepository.save(newOrder);
        return savedOrder;
    }


    public Orders updateOrder(Orders order) {
        Orders existingOrder = ordersRepository.findById(order.getId()).get();
        existingOrder.setNote(order.getNote());
        existingOrder.setTotal(order.getTotal());
        Orders updatedOrder = ordersRepository.save(existingOrder);
        return updatedOrder;
    }

    public void deleteOrder(Long id) {
        ordersRepository.deleteOrderById(id);
    }

    public List<Orders> findAllOrders() {
        return ordersRepository.findAll().stream()
                .filter(order -> !order.getIsDeleted())
                .collect(Collectors.toList());
    }

    public Orders findOrderById(Long id) {
        return ordersRepository.findOrderById(id).orElseThrow(() -> new OrderNotFoundException("Order with this id " + id + "does not exist"));
    }

    public List<OrderDetails> findDetailsByOrdersID(Long orderID) {
        Orders order = ordersRepository.findById(orderID)
                .orElseThrow(() -> new OrderNotFoundException("DETAILS with this id " + orderID + " does not exist"));
        return order.getOrderDetail();
    }
}
