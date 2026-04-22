package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        double initialBalance = Double.valueOf(request.get("initialBalance").toString());
        String accountType = request.get("accountType").toString();
        Account account = accountService.createAccount(customerId, initialBalance, accountType);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomer(customerId);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        accountService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        accountService.withdraw(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody Map<String, Object> request) {
        Long fromAccountId = Long.valueOf(request.get("fromAccountId").toString());
        Long toAccountId = Long.valueOf(request.get("toAccountId").toString());
        double amount = Double.valueOf(request.get("amount").toString());
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
        double balance = accountService.getBalance(id);
        return ResponseEntity.ok(balance);
    }
}
