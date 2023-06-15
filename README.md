# Spring-Boot-Rest-Api
Java Spring Boot Kullanarak Hazırlanmış demo  CRUD API Örneği 

VeriTabanı Bağlantısı ve Port Seçimi
	-main/resources/application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/CrudExampleDB     #CrudExampleDB  Şema adımız oluyor
spring.datasource.username=springstudent       #MySQL kullanıcı adı
spring.datasource.password=springstudent       #Şifresi
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver.   #sabit
spring.jpa.hibernate.ddl-auto=update        #Okuma ve Yazma tam izin sabit
server.port=8080   #Api için port 
```
