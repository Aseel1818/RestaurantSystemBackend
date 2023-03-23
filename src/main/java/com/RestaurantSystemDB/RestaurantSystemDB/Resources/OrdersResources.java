package com.RestaurantSystemDB.RestaurantSystemDB.Resources;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.ItemsServices;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.OrdersServices;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.TablesServices;
import com.RestaurantSystemDB.RestaurantSystemDB.payload.OrderDetailsPayload;
import com.RestaurantSystemDB.RestaurantSystemDB.payload.OrderPayload;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping()
public class OrdersResources {
    @Autowired
    private final OrdersServices ordersServices;
    @Autowired
    private TablesServices tablesServices;
    @Autowired
    private ItemsServices itemsServices;

    public OrdersResources(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersServices.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/findOrder/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        Orders order = ordersServices.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PostMapping("/addOrder")

    public ResponseEntity<Orders> addOrder(@RequestBody OrderPayload order) {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsPayload orderDetailPayload :
                order.getOrderDetail()) {
            orderDetails.add(
                    OrderDetails.builder()
                            .item(itemsServices.findItemById(orderDetailPayload.getItemId()))
                            .quantity(orderDetailPayload.getQuantity())
                            .build()
            );
        }
        Orders newOrder = Orders.builder()
                .tables(order.getTables() != null ? tablesServices.findTableById(order.getTables()) : null)
                .total(order.getTotal())
                .note(order.getNote())
                .orderDetail(orderDetails)
                .build();
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id,
                                              @RequestBody Orders order) {
        order.setId(id);
        Orders updatedOrder = ordersServices.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    @DeleteMapping("/deleteOrder/{id}")
    @Transactional
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        ordersServices.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<OrderDetails>> getDetailsByOrdersID(@PathVariable("id") Long orderID) {
        List<OrderDetails> orders = ordersServices.findDetailsByOrdersID(orderID);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
