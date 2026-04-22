package com.bankingsystem.service;

import com.bankingsystem.exception.NotFoundException;
import com.bankingsystem.exception.ValidationException;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Long customerId, double initialBalance, String accountType) {
        if (initialBalance < 0) {
            throw new ValidationException("Initial balance cannot be negative");
        }
        Account account = new Account(customerId, initialBalance, accountType);
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public List<Account> getAccountsByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Transactional
    public void deposit(Long accountId, double amount) {
        if (amount <= 0) {
            throw new ValidationException("Deposit amount must be positive");
        }
        Account account = getAccount(accountId);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(accountId, Transaction.TransactionType.DEPOSIT, amount, null, "Deposit");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void withdraw(Long accountId, double amount) {
        if (amount <= 0) {
            throw new ValidationException("Withdrawal amount must be positive");
        }
        Account account = getAccount(accountId);
        if (account.getBalance() < amount) {
            throw new ValidationException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(accountId, Transaction.TransactionType.WITHDRAWAL, amount, null, "Withdrawal");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        if (amount <= 0) {
            throw new ValidationException("Transfer amount must be positive");
        }
        if (fromAccountId.equals(toAccountId)) {
            throw new ValidationException("Cannot transfer to the same account");
        }
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);
        if (fromAccount.getBalance() < amount) {
            throw new ValidationException("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction debitTransaction = new Transaction(fromAccountId, Transaction.TransactionType.TRANSFER, amount, toAccountId, "Transfer to " + toAccountId);
        Transaction creditTransaction = new Transaction(toAccountId, Transaction.TransactionType.TRANSFER, amount, fromAccountId, "Transfer from " + fromAccountId);
        transactionRepository.save(debitTransaction);
        transactionRepository.save(creditTransaction);
    }

    public double getBalance(Long accountId) {
        return getAccount(accountId).getBalance();
    }
}
