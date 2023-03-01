package com.RestaurantSystemDB.RestaurantSystemDB.Services;
import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.OrderDetailNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDetailsServices {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsServices(OrderDetailsRepository orderDetailsRepository){
        this.orderDetailsRepository=orderDetailsRepository;
    }

    public OrderDetails addOrderDetail (OrderDetails orderDetail){
        return  orderDetailsRepository.save(orderDetail);

    }


    public OrderDetails updateOrderDetail(OrderDetails orderDetail) {
        OrderDetails existingOrderDetail = orderDetailsRepository.findById(orderDetail.getOrderDetailId()).get();
        existingOrderDetail.setNoteForMenu(orderDetail.getNoteForMenu());
        existingOrderDetail.setCurrency(orderDetail.getCurrency());
        existingOrderDetail.setQuantity(orderDetail.getQuantity());
        OrderDetails updatedOrderDetail = orderDetailsRepository.save(existingOrderDetail);
        return updatedOrderDetail;
    }

    public void deleteOrderDetail(Long id){
        orderDetailsRepository.deleteOrderDetailById(id);
    }

    public List<OrderDetails> findAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails findOrderDetailById(Long id ){
        return orderDetailsRepository.findOrderDetailById(id).orElseThrow(()->new OrderDetailNotFoundException("Order Detail with this id "+id+"does not exist"));


    }
}
