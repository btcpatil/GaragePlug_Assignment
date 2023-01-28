package com.garageplug.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garageplug.models.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

}
