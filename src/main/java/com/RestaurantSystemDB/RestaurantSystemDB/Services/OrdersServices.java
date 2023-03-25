package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.OrderNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.OrdersRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.payload.OrderPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.RestaurantSystemDB.RestaurantSystemDB.payload.OrderDetailsPayload;

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
        List<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsPayload orderDetailPayload : orderPayload.getOrderDetail()) {
            OrderDetails orderDetail = OrderDetails.builder()
                    .item(itemsServices.findItemById(orderDetailPayload.getItemId()))
                    .quantity(orderDetailPayload.getQuantity())
                    .build();
            orderDetails.add(orderDetail);
        }

        Orders newOrder = Orders.builder()
                .id(orderPayload.getId())
                .tables(orderPayload.getTables() != null ? tablesServices.findTableById(orderPayload.getTables()).getID() : null)
                .total(orderPayload.getTotal())
                .note(orderPayload.getNote())
                .orderDetail(orderDetails)
                .build();
        for (OrderDetails orderDetail : orderDetails) {
            orderDetail.setOrder(newOrder);
        }
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
        return ordersRepository.findAll();
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
