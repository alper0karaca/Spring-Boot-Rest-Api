package com.alperkaraca.CrudExample.service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alperkaraca.CrudExample.dataAccess.CustomerRepository;
import com.alperkaraca.CrudExample.entity.Customer;
import com.alperkaraca.CrudExample.service.abstracts.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	// müşteri ekleme
	@Override
	public Customer addCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}

	// tüm müşterileri getirme
	@Override
	public List<Customer> findAllCustomer() {
		
		
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long customerid) {
		
		
		return customerRepository.findById(customerid).get();
	}

	@Override
	public void deleteCustomer(Long customerid) {
	
		customerRepository.deleteById(customerid);
		
	}

}
