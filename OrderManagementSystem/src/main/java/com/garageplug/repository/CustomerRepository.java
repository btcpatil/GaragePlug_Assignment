package com.garageplug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garageplug.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	@Query("select case when count(c) > 0 then true else false end from customer c where c.customerId = ?1")
//	Boolean isCustomerExists(Integer id);
}
