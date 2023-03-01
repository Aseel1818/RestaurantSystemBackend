package com.RestaurantSystemDB.RestaurantSystemDB.Resources;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.OrdersServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Orders")
public class OrdersResources
{
    private final OrdersServices ordersServices;

    public OrdersResources(OrdersServices ordersServices){
        this.ordersServices=ordersServices;
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> orders = ordersServices.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/findOrder/{id}")
    public ResponseEntity<Orders> getOrderById (@PathVariable("id") Long id){
        Orders order = ordersServices.findOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }



    @PostMapping("/addOrder")

    public ResponseEntity<Orders> addOrder(@RequestBody Orders order){
        Orders  newOrder = ordersServices.addOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id,
                                                     @RequestBody Orders order){
        order.setOrderId(id);
        Orders updatedOrder = ordersServices.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    @DeleteMapping("/deleteOrder/{id}")
    @Transactional
    public ResponseEntity<?> deleteOrder (@PathVariable("id") Long id ){
        ordersServices.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
