package net.abid.accountservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import net.abid.accountservice.clients.CustomerRestClient;
import net.abid.accountservice.entities.BankAccount;
import net.abid.accountservice.enums.AccountType;
import net.abid.accountservice.model.CustomerModel;
import net.abid.accountservice.repository.BankAccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Account Service API")
						.version("1.0")
						.description("API documentation for Account Service"));
	}

	@Bean
	public CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
		return args -> {
			customerRestClient.getAllCustomers().forEach(customer -> {
				BankAccount bankAccount1 = new BankAccount();
				bankAccount1.setAccountId(UUID.randomUUID().toString());
				bankAccount1.setAccountType(AccountType.CURRENT_ACCOUNT);
				bankAccount1.setBalance(Math.random() * 50000);
				bankAccount1.setCurrency("EUR");
				bankAccount1.setCreateDate(LocalDate.now());
				bankAccount1.setCustomerId(customer.getId());
				bankAccount1.setCustomer(new CustomerModel());
				bankAccountRepository.save(bankAccount1);
				BankAccount bankAccount2 = new BankAccount();
				bankAccount2.setAccountId(UUID.randomUUID().toString());
				bankAccount2.setAccountType(AccountType.CURRENT_ACCOUNT);
				bankAccount2.setBalance(Math.random() * 100000);
				bankAccount2.setCurrency("MAD");
				bankAccount2.setCreateDate(LocalDate.now());
				bankAccount2.setCustomerId(customer.getId());
				bankAccount2.setCustomer(new CustomerModel());
				CustomerModel customerModel = bankAccount2.getCustomer();
				System.out.println("bankAccount's customerservice: " + customerModel.getId());
				bankAccountRepository.save(bankAccount2);

			});

		};
	}
}
