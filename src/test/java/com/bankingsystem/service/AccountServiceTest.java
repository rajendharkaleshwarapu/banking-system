package com.bankingsystem.service;

import com.bankingsystem.exception.NotFoundException;
import com.bankingsystem.exception.ValidationException;
import com.bankingsystem.model.Account;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.main.BankingSystemApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BankingSystemApplication.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account(1L, 100.0, "SAVINGS");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account created = accountService.createAccount(1L, 100.0, "SAVINGS");
        assertNotNull(created);
        assertEquals(100.0, created.getBalance());
    }

    @Test
    void testGetAccount() {
        Account account = new Account(1L, 100.0, "SAVINGS");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account found = accountService.getAccount(1L);
        assertNotNull(found);
        assertEquals(100.0, found.getBalance());
    }

    @Test
    void testGetAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountService.getAccount(1L));
    }

    @Test
    void testDeposit() {
        Account account = new Account(1L, 100.0, "SAVINGS");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.deposit(1L, 50.0);
        assertEquals(150.0, account.getBalance());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void testWithdraw() {
        Account account = new Account(1L, 100.0, "SAVINGS");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.withdraw(1L, 50.0);
        assertEquals(50.0, account.getBalance());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        Account account = new Account(1L, 50.0, "SAVINGS");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(ValidationException.class, () -> accountService.withdraw(1L, 100.0));
    }
}
