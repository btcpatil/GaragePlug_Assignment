package com.garageplug;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.garageplug.enums.Category;
import com.garageplug.exception.CustomerException;
import com.garageplug.exception.OrderException;
import com.garageplug.models.Customer;
import com.garageplug.models.OrderEntity;
import com.garageplug.repository.CustomerRepository;
import com.garageplug.repository.OrderRepository;
import com.garageplug.service.CustomerService;
import com.garageplug.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderManagementSystemApplicationTests {

	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerRepository customerActualRepo;
	
    @MockBean
	private CustomerRepository customerMockRepo;
    
    @MockBean
    private OrderRepository orderMockRepo;
	
	@Test
	public void getAllCustomers() {
		when(customerMockRepo.findAll()).thenReturn(Stream
				.of(new Customer("manju","89438493",10,Category.GOLD),new Customer("raju","378473843",5,Category.REGULAR))
				.collect(Collectors.toList()));
		assertEquals(2, customerservice.getAllCustomers().size());
	}
	
	@Test
	public void getAllOrders() throws OrderException {
		when(orderMockRepo.findAll()).thenReturn(Stream
				.of(new OrderEntity(1912.0,12),new OrderEntity(100.0,22)).collect(Collectors.toList()));
		assertEquals(2, orderService.getAllOrders().size());
	}
	
	
	

}
