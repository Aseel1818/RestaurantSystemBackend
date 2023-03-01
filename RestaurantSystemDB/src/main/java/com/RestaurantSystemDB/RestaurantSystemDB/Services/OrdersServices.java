package com.RestaurantSystemDB.RestaurantSystemDB.Services;
import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.OrderNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdersServices {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServices(OrdersRepository ordersRepository){
        this.ordersRepository=ordersRepository;
    }

    public Orders addOrder (Orders order){
        return  ordersRepository.save(order);

    }


    public Orders updateOrder(Orders order) {
        Orders existingOrder = ordersRepository.findById(order.getOrderID()).get();
        existingOrder.setOrderNote(order.getOrderNote());
        existingOrder.setTotal(order.getTotal());
        Orders updatedOrder = ordersRepository.save(existingOrder);
        return updatedOrder;
    }

    public void deleteOrder(Long id){
        ordersRepository.deleteOrderById(id);
    }

    public List<Orders> findAllOrders(){
        return ordersRepository.findAll();
    }

    public Orders findOrderById(Long id ){
        return ordersRepository.findOrderById(id).orElseThrow(()->new OrderNotFoundException("Order with this id "+id+"does not exist"));


    }
}
