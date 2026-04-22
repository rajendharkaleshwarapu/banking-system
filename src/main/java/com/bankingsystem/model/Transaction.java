package com.bankingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "account_id")
    private Long accountId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, WITHDRAWAL, TRANSFER

    @Positive
    private double amount;

    @Column(name = "target_account_id")
    private Long targetAccountId; // For transfers

    private String description;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction() {}

    public Transaction(Long accountId, TransactionType type, double amount, Long targetAccountId, String description) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.targetAccountId = targetAccountId;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Long getTargetAccountId() { return targetAccountId; }
    public void setTargetAccountId(Long targetAccountId) { this.targetAccountId = targetAccountId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }
}
