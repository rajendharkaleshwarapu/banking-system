package com.bankingsystem.util;

import com.bankingsystem.exception.ValidationException;

public class ValidationUtil {

    public static void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("Invalid email format");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new ValidationException("Phone number must be 10 digits");
        }
    }

    public static void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new ValidationException("Amount must be positive");
        }
    }
}
