package com.springms.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springms.orderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
