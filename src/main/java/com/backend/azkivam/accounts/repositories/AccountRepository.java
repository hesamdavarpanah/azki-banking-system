package com.backend.azkivam.accounts.repositories;

import com.backend.azkivam.accounts.models.BankAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BankAccount> findBankAccountByAccountNumber(String accountNumber);
}
