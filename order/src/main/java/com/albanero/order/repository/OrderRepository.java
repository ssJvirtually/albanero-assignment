package com.albanero.order.repository;

import com.albanero.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM order_table o WHERE o.event_type = 'ORDER' ORDER BY o.id DESC LIMIT 5", nativeQuery = true)
    List<Order> findLastFiveOrders();
}
