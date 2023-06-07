package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.OrdersServices;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.OrderPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/order")
public class OrdersController {
    @Autowired
    private final OrdersServices ordersServices;

    public OrdersController(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersServices.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

    @GetMapping("/findOrder/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        Orders order = ordersServices.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/addOrder")
    public ResponseEntity<Orders> addOrder(@RequestBody OrderPayload order) {
        Orders savedOrder = ordersServices.addOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/details")

    public ResponseEntity<List<OrderDetails>> getDetailsByOrdersID(@PathVariable("id") Long orderID) {
        List<OrderDetails> orders = ordersServices.findDetailsByOrdersID(orderID);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id,
                                              @RequestBody Orders order) {
        order.setId(id);
        Orders updatedOrder = ordersServices.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
