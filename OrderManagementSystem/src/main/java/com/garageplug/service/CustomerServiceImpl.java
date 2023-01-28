package com.garageplug.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garageplug.Dto.CustomerDto;
import com.garageplug.enums.Category;
import com.garageplug.exception.CustomerException;
import com.garageplug.exception.DiscountException;
import com.garageplug.models.Customer;
import com.garageplug.models.DiscountDetails;
import com.garageplug.repository.CustomerRepository;
import com.garageplug.repository.DiscountDetailsRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	DiscountDetailsRepository discountRepo;

	/*
	 * This is the implementation to add new customer.
	 */
	@Override
	public String addCustomer(CustomerDto customerDto) {
		int initialOrderCount = 0;
		
		Customer newCustomer = new Customer();
		newCustomer.setCustomerName(customerDto.getCustomerName());
		newCustomer.setNumberOfOrders(initialOrderCount);
		newCustomer.setPhoneNumber(customerDto.getPhoneNumber());
		newCustomer.setCategoryType(Category.REGULAR);
		
		customerRepo.save(newCustomer);
		
		return "customer is saved successfully";
	}

	/*
	 * This is the implementation to get all the customers from the database.
	 */
	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepo.findAll();
	}

	/*
	 * This is the implementation to get customer by id.
	 */
	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {
		
		Optional<Customer> customerOption = customerRepo.findById(customerId);
		
		if(customerOption.isPresent()) {
			return customerOption.get();
		}
		else {
			throw new CustomerException("Customer is doesn't exists with id " +customerId);
		}
	}

	/*
	 * This is implementation to get discount details.
	 */
	@Override
	public List<DiscountDetails> getDiscountDetails() throws DiscountException {
		
		List<DiscountDetails> discountList = discountRepo.findAll();
		
		if(discountList.size() == 0) {
			throw new DiscountException("No one eligible for discount");
		}
		else {
			return discountList;
		}
	}
	
	
	

}
