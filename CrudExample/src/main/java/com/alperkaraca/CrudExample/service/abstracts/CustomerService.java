package com.alperkaraca.CrudExample.service.abstracts;

import java.util.List;

import com.alperkaraca.CrudExample.entity.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	
	List<Customer> findAllCustomer();
	
	Customer getCustomerById(Long customerid);
	
	void deleteCustomer(Long customerid);

}
