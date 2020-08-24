package org.sid.customerservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;




@Entity

 class Customer{
	 
	@Id @GeneratedValue
	 private Long id;
	 private String name;
	 private String email;
	public Customer(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	 
	
}

@RepositoryRestResource
interface CustomerRepositorry extends JpaRepository<Customer, Long>{}

@Projection(name = "p1",types = Customer.class)
interface CustomerProjection{
	public Long getId();
	public String getName();
}
@SpringBootApplication
public class CustomerServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	

	@Bean
	CommandLineRunner stert(CustomerRepositorry cRepositorry,RepositoryRestConfiguration restConfiguration) {
		return args->{
			restConfiguration.exposeIdsFor(Customer.class);
			cRepositorry.save(new Customer(null,"ENSET","enset@gmail.com"));
			cRepositorry.save(new Customer(null,"ibm","ibm@gmail.com"));
			cRepositorry.save(new Customer(null,"hp","hp@gmail.com"));
			cRepositorry.findAll().forEach(System.out::println);
		};
	}
}
