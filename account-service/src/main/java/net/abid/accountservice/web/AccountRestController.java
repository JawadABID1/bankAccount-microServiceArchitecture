package net.abid.accountservice.web;

import net.abid.accountservice.clients.CustomerRestClient;
import net.abid.accountservice.entities.BankAccount;
import net.abid.accountservice.model.CustomerModel;
import net.abid.accountservice.repository.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        });

        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getAccountById(@PathVariable String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount != null) {
            CustomerModel customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setCustomer(customer);
        }
        return bankAccount;
    }

    @PostMapping("/accounts/create")
    public BankAccount addNewAccount(@RequestBody BankAccount newBankAccount) {
        return bankAccountRepository.save(newBankAccount);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable String id, @RequestBody BankAccount updatedAccount) {
        BankAccount existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with Id: " + id));

        // Update the fields
        existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setBalance(updatedAccount.getBalance());
        existingAccount.setCurrency(updatedAccount.getCurrency());
        existingAccount.setCustomerId(updatedAccount.getCustomerId());

        // Set updatedDate to current date if it's not included in the request
//        if (updatedAccount.getUpdatedDate() != null) {
//            existingAccount.setUpdatedDate(updatedAccount.getUpdatedDate());
//        } else {
//            existingAccount.setUpdatedDate(LocalDate.now());
//        }

        // Save and return the updated account
        BankAccount updated = bankAccountRepository.save(existingAccount);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        BankAccount accountToDelete = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with Id: " + id));

        bankAccountRepository.delete(accountToDelete);
        return ResponseEntity.noContent().build();
    }
}
