package com.RestaurantSystemDB.RestaurantSystemDB.Resources;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.OrderDetailsServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/OrderDetails")
public class OrderDetailsResources {

    private final OrderDetailsServices orderDetailsServices;

    public OrderDetailsResources(OrderDetailsServices orderDetailsServices){
        this.orderDetailsServices=orderDetailsServices;
    }

    @GetMapping("/allOrderDetails")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails(){
        List<OrderDetails> orderDetails = orderDetailsServices.findAllOrderDetails();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/findOrderDetail/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailById (@PathVariable("id") Long id){
        OrderDetails orderDetail = orderDetailsServices.findOrderDetailById(id);
        return new ResponseEntity<>(orderDetail,HttpStatus.OK);
    }



    @PostMapping("/addOrderDetail")

    public ResponseEntity<OrderDetails> addOrderDetail(@RequestBody OrderDetails orderDetail){
        OrderDetails  newOrderDetail = orderDetailsServices.addOrderDetail(orderDetail);
        return new ResponseEntity<>(newOrderDetail,HttpStatus.CREATED);
    }

    @PutMapping("/updateOrderDetail/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetail(@PathVariable("id") Long id,
                                                       @RequestBody OrderDetails orderDetail){
        orderDetail.setOrderDetailId(id);
        OrderDetails updatedOrderDetail = orderDetailsServices.updateOrderDetail(orderDetail);
        return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
    }


    @DeleteMapping("/deleteOrderDetail/{id}")
    @Transactional
    public ResponseEntity<?> deleteOrderDetail (@PathVariable("id") Long id ){
        orderDetailsServices.deleteOrderDetail(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
