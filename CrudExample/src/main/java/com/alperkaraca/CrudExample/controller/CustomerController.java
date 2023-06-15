package com.alperkaraca.CrudExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alperkaraca.CrudExample.entity.Customer;
import com.alperkaraca.CrudExample.exception.CustomerErrorResponse;
import com.alperkaraca.CrudExample.exception.CustomerNotFoundException;
import com.alperkaraca.CrudExample.service.abstracts.CustomerService;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		
		
		Customer addCustomer = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(addCustomer,HttpStatus.CREATED) ;
		
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		
		List<Customer> allCustomers = customerService.findAllCustomer();
		
		
		return new ResponseEntity<List<Customer>>(allCustomers,HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		
		Customer customerById = customerService.getCustomerById(id);
		
		if (customerById==null) {
			throw new CustomerNotFoundException("Customer Id Not Found :"+id);
			
		}
		
		return new ResponseEntity<Customer>(customerById,HttpStatus.OK) ;
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id){
		
		Customer customerById = customerService.getCustomerById(id);
		
		if (customerById==null) {
			throw new CustomerNotFoundException("Customer Id Not Found :"+id);
			
		}
		
		 customerService.deleteCustomer(id);
	        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		
	}
	

}
