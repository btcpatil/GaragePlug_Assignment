package com.garageplug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garageplug.exception.CustomerException;
import com.garageplug.exception.OrderException;
import com.garageplug.models.OrderEntity;
import com.garageplug.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	/*
	 * This api create new order and it will take customerId and amount from uri.
	 */
	@PostMapping("/cust_id/{customerId}/amount/{amount}")
	public ResponseEntity<OrderEntity> placeOrder(@PathVariable Integer customerId, @PathVariable Double amount) throws CustomerException{
		OrderEntity result = orderService.placeOrder(customerId, amount);
		return new ResponseEntity<OrderEntity>(result, HttpStatus.CREATED);
	}
	
	/*
	 * This api will fetch all orders present in the database.
	 */
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderEntity>> getAllOrders() throws OrderException{
		
		 List<OrderEntity> orderList = orderService.getAllOrders();
		 
		 return new ResponseEntity<List<OrderEntity>>(orderList, HttpStatus.ACCEPTED);
	}
	
	/*
	 * This api will give the order by Id.
	 */
	@GetMapping("/order_id/{orderId}")
	public ResponseEntity<OrderEntity> findOrderById(@PathVariable Integer orderId) throws OrderException {
		OrderEntity order = orderService.findOrderById(orderId);
		return new ResponseEntity<OrderEntity>(order, HttpStatus.ACCEPTED);
	}
	
}
