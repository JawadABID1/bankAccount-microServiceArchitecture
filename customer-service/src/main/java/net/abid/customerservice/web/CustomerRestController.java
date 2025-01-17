package net.abid.customerservice.web;

import net.abid.customerservice.entities.Customer;
import net.abid.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/customers/create")
    public Customer addCustomer(@RequestBody Customer newCustomer){
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/customers/{id}")
    public Customer editCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer){
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        return customerRepository.save(existingCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found with ID: " + id));
        customerRepository.delete(customerToDelete);
    }

}
