package com.garageplug.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.garageplug.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String customerName;
	private String phoneNumber;
	private Integer numberOfOrders;
	private Category categoryType;
	
	public Customer(String customerName,String phoneNumber,Integer numberOfOrders,Category categoryType){
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.numberOfOrders = numberOfOrders;
		this.categoryType = categoryType;
	}
	
}
