package com.garageplug.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garageplug.enums.Category;
import com.garageplug.exception.CustomerException;
import com.garageplug.exception.OrderException;
import com.garageplug.models.Customer;
import com.garageplug.models.DiscountDetails;
//import com.garageplug.models.DiscountDetails;
import com.garageplug.models.OrderEntity;
import com.garageplug.repository.CustomerRepository;
import com.garageplug.repository.DiscountDetailsRepository;
//import com.garageplug.repository.DiscountDetailsRepository;
import com.garageplug.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	DiscountDetailsRepository discountReopo;
	
	@Override
	public OrderEntity placeOrder(Integer customerId, Double orderAmount) throws CustomerException {
		
		OrderEntity newOrder = new OrderEntity();
		Optional<Customer> customerOptional = customerRepo.findById(customerId);
		if(customerOptional.isPresent()) {
			Customer existingCustomer = customerOptional.get();
			
			updateExistingCustomer(existingCustomer);
			newOrder.setCustomer(existingCustomer);
			newOrder.setOrderAmount(orderAmount);
			newOrder.setCustomerOrderCount(existingCustomer.getNumberOfOrders());
			OrderEntity savedOrder = orderRepo.save(newOrder);
			
		    updateDiscountEntity(newOrder);
			
			return savedOrder;
		}
		throw new CustomerException("customer doesn't exists with id "+ customerId);
	}
	
	/*
	 * This method is to update the category of the customer.
	 * And it will send promotional mail to customer about the discount information.
	 */
	public void updateExistingCustomer(Customer customer) {
		
		int orderCount = customer.getNumberOfOrders()+1;
		if(orderCount == 9 || orderCount == 19) {
			System.out.println("Mail is sent to the customer");
		}
		
		customer.setNumberOfOrders(orderCount);
		if(orderCount >= 10 && orderCount <= 19) {
			customer.setCategoryType(Category.GOLD);
		}
		else if(orderCount >= 20) {
			customer.setCategoryType(Category.PLATINUM);
		}
		
	}
	
	
	/*
	 * This method will apply the discount to the customer according the category they are in.
	 */
	public void updateDiscountEntity(OrderEntity order) {
		
		Customer customer = order.getCustomer();
		DiscountDetails addDiscount  = new DiscountDetails();
		int customerOrderCount = customer.getNumberOfOrders();
		if(customerOrderCount >= 10 && customerOrderCount <= 19) {
			addDiscount.setDiscountPercentage(10);
			addDiscount.setOrder(order);
			discountReopo.save(addDiscount);
		}
		else if(customerOrderCount >= 20) {
			addDiscount.setDiscountPercentage(20);
			addDiscount.setOrder(order);
			discountReopo.save(addDiscount);
		}
		
	}
	
	/*
	 * This is the implementation to get all orders present in the database.
	 */
	@Override
	public List<OrderEntity> getAllOrders() throws OrderException {
		
		List<OrderEntity> orderList = orderRepo.findAll();
		if(orderList.size() == 0) {
			throw new OrderException("No order placed it now!!!!");
		}
		else {
			return orderList;
		}
	}

	/*
	 * This is the implementation to get all orders by id.
	 */
	@Override
	public OrderEntity findOrderById(Integer orderId) throws OrderException {
		
		Optional<OrderEntity> order = orderRepo.findById(orderId);
		if(order.isPresent()) {
			return order.get();
		}
		else {
			throw new OrderException("Order doesn't exists with id "+ orderId);
		}
	}

}
