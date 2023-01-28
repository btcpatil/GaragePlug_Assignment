package com.garageplug.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.garageplug.Dto.CustomerDto;
import com.garageplug.exception.CustomerException;
import com.garageplug.exception.DiscountException;
import com.garageplug.models.Customer;
import com.garageplug.models.DiscountDetails;

public interface CustomerService {
	
    public String addCustomer(CustomerDto customerDto);
	
    public List<Customer> getAllCustomers();
    
    public Customer getCustomerById(Integer customerId)throws CustomerException;
    
    public List<DiscountDetails> getDiscountDetails()throws DiscountException;
}
