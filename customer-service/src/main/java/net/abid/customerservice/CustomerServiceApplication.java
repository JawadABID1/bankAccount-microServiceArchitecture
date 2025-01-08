package net.abid.customerservice;

import net.abid.customerservice.config.GlobalConfig;
import net.abid.customerservice.entities.Customer;
import net.abid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
//			List<Customer> customers = List.of(
//					Customer.builder()
//							.firstName("Jawad")
//							.lastName("ABID")
//							.build(),
//					Customer.builder()
//							.firstName("Mohamed")
//							.lastName("ABID")
//							.build()
//			);


//			Customer customer1 = Customer.builder()
//					.firstName("Jawad")
//					.lastName("ABID")
//					.email("jawad@abid")
//					.build();
//			Customer customer2 = Customer.builder()
//					.firstName("Kamal")
//					.lastName("ABID")
//					.email("kamal@abid")
//					.build();
			Customer customer1 = new Customer();
			customer1.setFirstName("Jawad");
			customer1.setLastName("ABID");
			customer1.setEmail("jawad.abid@gmail.com");
			customerRepository.save(customer1);
			Customer customer2 = new Customer();
			customer2.setFirstName("Kamal");
			customer2.setLastName("ABID");
			customer2.setEmail("kamal.abid@gmail.com");
			customerRepository.save(customer2);

		};
	}


}
