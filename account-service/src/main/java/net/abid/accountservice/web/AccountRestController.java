package net.abid.accountservice.web;

import net.abid.accountservice.clients.CustomerRestClient;
import net.abid.accountservice.entities.BankAccount;
import net.abid.accountservice.model.CustomerModel;
import net.abid.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accounts() {

        List <BankAccount> bankAccounts =  bankAccountRepository.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        }
        );

        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getAccountById(@PathVariable String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        CustomerModel customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
