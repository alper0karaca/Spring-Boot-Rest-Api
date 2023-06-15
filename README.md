# Spring-Boot-Rest-Api
Java Spring Boot Kullanarak Hazırlanmış demo  CRUD API Örneği 

1 - VeriTabanı Bağlantısı ve Port Seçimi               
	-main/resources/application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/CrudExampleDB     #CrudExampleDB  Şema adımız oluyor
spring.datasource.username=springstudent       #MySQL kullanıcı adı
spring.datasource.password=springstudent       #Şifresi
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver.   #sabit
spring.jpa.hibernate.ddl-auto=update        #Okuma ve Yazma tam izin sabit
server.port=8080   #Api için port 
```
<br>
2 - Entity Sınıfını oluşturmak

```
package com.alperkaraca.CrudExample.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "customer")
@Getter
@Setter
@ToString
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName; 
	
	@Column(name = "last_name")
	private String lastName;
} 
```
<br>
3- Entity katmanlarının repostiorylerini hazırlayalım

```
package com.alperkaraca.CrudExample.dataAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alperkaraca.CrudExample.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
```
<br>
4 - Service Katmanlarını Hazırlayalım 

// buraya iş kodlarını yazıyoruz

``` 
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

	// id ile müşteri bulma
	@Override
	public Customer getCustomerById(Long customerid) {
		
		
		return customerRepository.findById(customerid).get();
	}

	
	// id ile müşteri silme
	@Override
	public void deleteCustomer(Long customerid) {
	
		customerRepository.deleteById(customerid);	
	}
} 
```
<br> 

5 - controller sınıfını yazalım ve endpointleri isimlendirelim 

```package com.alperkaraca.CrudExample.controller;

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
}}




