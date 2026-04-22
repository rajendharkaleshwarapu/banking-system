-- Minimal data.sql
-- Insert initial data here

-- Insert initial data for testing

-- Insert customers
INSERT INTO customers (name, email, phone, created_at, updated_at) VALUES
('John Doe', 'john.doe@example.com', '1234567890', NOW(), NOW()),
('Jane Smith', 'jane.smith@example.com', '0987654321', NOW(), NOW());

-- Insert accounts
INSERT INTO accounts (customer_id, balance, account_type, created_at, updated_at) VALUES
(1, 1000.00, 'SAVINGS', NOW(), NOW()),
(1, 500.00, 'CHECKING', NOW(), NOW()),
(2, 2000.00, 'SAVINGS', NOW(), NOW());

-- Insert transactions
INSERT INTO transactions (account_id, type, amount, description, transaction_date) VALUES
(1, 'DEPOSIT', 1000.00, 'Initial deposit', NOW()),
(2, 'DEPOSIT', 500.00, 'Initial deposit', NOW()),
(3, 'DEPOSIT', 2000.00, 'Initial deposit', NOW());
