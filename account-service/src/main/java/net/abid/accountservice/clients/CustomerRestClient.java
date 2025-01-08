package net.abid.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.abid.accountservice.model.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomerDefault")
    CustomerModel getCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomersDefault")
    List<CustomerModel> getAllCustomers();

    default CustomerModel getCustomerDefault(Long id, Exception ex) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(id);
        customerModel.setFirstName("no available");
        customerModel.setLastName("no available");
        customerModel.setEmail("no available");
        return customerModel;
    }

    default List<CustomerModel> getAllCustomersDefault(Exception ex) {
        return List.of();
    }
}
