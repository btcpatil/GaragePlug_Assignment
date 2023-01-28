package com.garageplug.service;

import java.util.List;

import com.garageplug.exception.CustomerException;
import com.garageplug.exception.OrderException;
import com.garageplug.models.OrderEntity;

public interface OrderService {

	public OrderEntity placeOrder(Integer customerId, Double orderAmount)throws CustomerException;
	
	public List<OrderEntity> getAllOrders()throws OrderException;
	
	public OrderEntity findOrderById(Integer orderId)throws OrderException;
}
