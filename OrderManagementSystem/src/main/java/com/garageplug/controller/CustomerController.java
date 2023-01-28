package com.garageplug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garageplug.Dto.CustomerDto;
import com.garageplug.exception.CustomerException;
import com.garageplug.exception.DiscountException;
import com.garageplug.models.Customer;
import com.garageplug.models.DiscountDetails;
import com.garageplug.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	
	/*
	 * This api will add new customer.
	 * Here customer details sent through customerDto object.
	 */
	@PostMapping("/add_customer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {
		String message = customerService.addCustomer(customerDto);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	/*
	 * This api will get all customer stored in the database.
	 */
	@GetMapping("/findAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customerList = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
	}
	
	/*
	 * This api will get the customer by Id.
	 * It will take customer id in the uri. If the customer not present with the specified id customerException will be thrown with proper message.
	 */
	@GetMapping("/customer/id/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) throws CustomerException{
		Customer existingCustomer = customerService.getCustomerById(id);
		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.ACCEPTED);
	}
	
	/*
	 * This api will give the discount information, how much discount is given to which customer on which order.
	 * Only GOLD, PLATINUM customers are eligible for discount.
	 */
	@GetMapping("/customers/dicountDetails")
	public ResponseEntity<List<DiscountDetails>> getDiscountDetails() throws DiscountException{
		
		List<DiscountDetails> discountList = customerService.getDiscountDetails();
		
		return new ResponseEntity<List<DiscountDetails>>(discountList,HttpStatus.ACCEPTED);
		
	}
	
}
