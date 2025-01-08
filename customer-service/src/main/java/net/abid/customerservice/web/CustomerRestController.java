package net.abid.customerservice.web;

import net.abid.customerservice.entities.Customer;
import net.abid.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {
    private CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
//        System.out.println("getAllCustomersStart");
        List<Customer> all = customerRepository.findAll();
//        all.forEach(System.out::println);
//        System.out.println("getAllCustomersEnd");
        return all;
    }


    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long  id) {
        return customerRepository.findById(id).get();
    }
}
