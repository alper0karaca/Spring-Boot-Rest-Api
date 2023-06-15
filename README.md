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



