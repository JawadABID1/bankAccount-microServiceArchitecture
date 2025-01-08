package net.abid.accountservice.repository;

import net.abid.accountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
