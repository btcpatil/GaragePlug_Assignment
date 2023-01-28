package com.garageplug.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Double orderAmount;
	private Integer customerOrderCount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Customer customer;
	
	
	public OrderEntity(Double orderAmount,Integer customerOrderCount) {
		this.orderAmount = orderAmount;
		this.customerOrderCount = customerOrderCount;
	}
	
}
