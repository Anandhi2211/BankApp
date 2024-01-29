drop database bankapp;
CREATE DATABASE IF NOT EXISTS bankapp;
USE bankapp;

CREATE TABLE IF NOT EXISTS customers (
  ssn BIGINT(10) NOT NULL  PRIMARY KEY,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone_number VARCHAR(45) NOT NULL
  );
  
  CREATE TABLE IF NOT EXISTS login_credentials(
  username VARCHAR(45) NOT NULL  PRIMARY KEY,
  active_status TINYINT(1) NOT NULL,
  user_password VARCHAR(45) NOT NULL,
  pin INT(4) NULL,
  customers_ssn BIGINT(10) NOT NULL,
  INDEX fk_logincredentials_customers1_idx (customers_ssn ASC),
  CONSTRAINT fk_logincredentials_customers1
    FOREIGN KEY (customers_ssn)
    REFERENCES customers (ssn)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
    CREATE TABLE IF NOT EXISTS accounts (
  account_number BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  total_balance DECIMAL(20, 2) NULL,
  logincredentials_username VARCHAR(45) NOT NULL,
  UNIQUE INDEX account_number_UNIQUE (account_number ASC),
  INDEX fk_accounts_logincredentials1_idx (logincredentials_username ASC),
  CONSTRAINT fk_accounts_logincredentials1
    FOREIGN KEY (logincredentials_username)
    REFERENCES login_credentials (username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
    CREATE TABLE IF NOT EXISTS savings_accounts (
  savings_balance DECIMAL(20, 2) NOT NULL,
  account_number BIGINT(20) UNSIGNED NOT NULL,
  interest_rate INT(10) NULL,
  INDEX fk_savingsaccounts_accounts1_idx (account_number ASC),
  CONSTRAINT fk_savingsaccounts_accounts1
    FOREIGN KEY (account_number)
    REFERENCES accounts (account_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
    CREATE TABLE IF NOT EXISTS debit_cards (
  card_number BIGINT(16) UNSIGNED NOT NULL PRIMARY KEY,
  expiration_date TIMESTAMP NOT NULL,
  cvv_number INT UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_full_name VARCHAR(50) NOT NULL,
  customers_ssn BIGINT(10) NOT NULL,
  UNIQUE INDEX card_number_UNIQUE (card_number ASC),
  UNIQUE INDEX cvv_number_UNIQUE (cvv_number ASC),
  INDEX fk_debitcards_customers1_idx (customers_ssn ASC),
  CONSTRAINT fk_debitcards_customers1
    FOREIGN KEY (customers_ssn)
    REFERENCES customers (ssn)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );

CREATE TABLE IF NOT EXISTS transactions (
  transaction_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(20, 2) NOT NULL,
  transaction_status TINYINT(1) NOT NULL,
  account_number BIGINT(20) UNSIGNED NOT NULL,
  transaction_timestamp TIMESTAMP NOT NULL,
  INDEX fk_transactions_accounts1_idx (account_number ASC),
  CONSTRAINT fk_transactions_accounts1
    FOREIGN KEY (account_number)
    REFERENCES Accounts (account_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
CREATE TABLE IF NOT EXISTS deposits (
  deposit_amount DECIMAL(20, 2) NOT NULL,
  account_number BIGINT(20) UNSIGNED NOT NULL,
  logincredentials_username VARCHAR(45) NOT NULL,
  transactions_transaction_id INT UNSIGNED NOT NULL,
  deposit_timestamp TIMESTAMP NOT NULL,
  INDEX fk_deposits_accounts1_idx (account_number ASC),
  INDEX fk_deposits_logincredentials1_idx (logincredentials_username ASC),
  INDEX fk_deposits_transactions1_idx (transactions_transaction_id ASC),
  CONSTRAINT fk_deposits_accounts1
    FOREIGN KEY (account_number)
    REFERENCES accounts (account_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_deposits_logincredentials1
    FOREIGN KEY (logincredentials_username)
    REFERENCES login_credentials (username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_deposits_transactions1
    FOREIGN KEY (transactions_transaction_id)
    REFERENCES transactions (transaction_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );

CREATE TABLE IF NOT EXISTS beneficiaries (
  beneficiary_name VARCHAR(45) NOT NULL,
  beneficiary_account_number BIGINT(20) NOT NULL,
  account_number BIGINT(20) UNSIGNED NOT NULL,
  INDEX fk_beneficiaries_accounts1_idx (account_number ASC),
  CONSTRAINT fk_beneficiaries_accounts1
    FOREIGN KEY (account_number)
    REFERENCES accounts (account_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
    CREATE TABLE IF NOT EXISTS withdrawals (
  withdrawal_amount DECIMAL(20, 2) NOT NULL,
  account_number BIGINT(20) UNSIGNED NOT NULL,
  logincredentials_username VARCHAR(45) NOT NULL,
  transactions_transaction_id INT UNSIGNED NOT NULL,
  withdrawal_timestamp TIMESTAMP NOT NULL,
  INDEX fk_withdrawals_accounts1_id (account_number ASC),
  INDEX fk_withdrawals_logincredentials1_idx (logincredentials_username ASC),
  INDEX fk_withdrawals_transactions1_idx (transactions_transaction_id ASC),
  CONSTRAINT fk_withdrawals_accounts1
    FOREIGN KEY (account_number)
    REFERENCES accounts (account_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_withdrawals_logincredentials1
    FOREIGN KEY (logincredentials_username)
    REFERENCES login_credentials (username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_withdrawals_transactions1
    FOREIGN KEY (transactions_transaction_id)
    REFERENCES transactions (transaction_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
CREATE TABLE IF NOT EXISTS bank_transfers (
  transfer_amount DECIMAL(20, 2) NOT NULL,
  beneficiary_account_number BIGINT(20) NOT NULL,
  charge DECIMAL(20, 2) NULL,
  logincredentials_username VARCHAR(45) NOT NULL,
  transactions_transaction_id INT UNSIGNED NOT NULL,
  transfer_timestamp TIMESTAMP NOT NULL,
  INDEX fk_banktransfers_logincredentials1_idx (logincredentials_username ASC),
  INDEX fk_banktransfers_transactions1_idx (transactions_transaction_id ASC),
  CONSTRAINT fk_banktransfers_logincredentials1
    FOREIGN KEY (logincredentials_username)
    REFERENCES login_credentials (username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_banktransfers_transactions1
    FOREIGN KEY (transactions_transaction_id)
    REFERENCES transactions (transaction_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
CREATE TABLE IF NOT EXISTS purchase_products(
  debitcards_card_number BIGINT(16) UNSIGNED NOT NULL,
  purchase_description VARCHAR(50) NULL,
  amount DECIMAL(20, 2) NULL,
  transactions_transaction_id INT UNSIGNED NOT NULL,
  customers_ssn BIGINT(10) NOT NULL,
  purchase_timestamp TIMESTAMP NOT NULL,
  INDEX fk_purchaseproducts_debitcards1_idx (debitcards_card_number ASC),
  INDEX fk_purchaseproducts_transactions1_idx (transactions_transaction_id ASC),
  INDEX fk_purchaseproducts_customers1_idx (customers_ssn ASC),
  CONSTRAINT fk_purchaseproducts_debitcards1
    FOREIGN KEY (debitcards_card_number)
    REFERENCES debit_cards (card_number)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_purchaseproducts_transactions1
    FOREIGN KEY (transactions_transaction_id)
    REFERENCES transactions (transaction_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_purchaseproducts_customers1
    FOREIGN KEY (customers_ssn)
    REFERENCES customers (ssn)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );
    
    CREATE TABLE IF NOT EXISTS payments (
  company_account_number BIGINT(20) NOT NULL  PRIMARY KEY,
  company_name VARCHAR(45) NOT NULL,
  bill_amount DECIMAL(20, 2) NULL,
  logincredentials_username VARCHAR(45) NOT NULL,
  transactions_transaction_id INT UNSIGNED NOT NULL,
  customers_ssn BIGINT(10) NOT NULL,
  payment_timestamp TIMESTAMP NOT NULL,
  INDEX fk_payments_logincredentials1_idx (logincredentials_username ASC),
  INDEX fk_payments_transactions1_idx (transactions_transaction_id ASC),
  INDEX fk_payments_customers1_idx (customers_ssn ASC),
  CONSTRAINT fk_payments_logincredentials1
    FOREIGN KEY (logincredentials_username)
    REFERENCES login_credentials (username)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payments_transactions1
    FOREIGN KEY (transactions_transaction_id)
    REFERENCES transactions (transaction_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_payments_customers1
    FOREIGN KEY (customers_ssn)
    REFERENCES customers (ssn)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );


SELECT * FROM customers;
INSERT INTO customers (ssn, `first_name`, `last_name`, `email`, `phone_number`)
VALUES
(123456789, 'John', 'Doe', 'john.doe@example.com', '123-456-7890'),
(122345789, 'Marko', 'Wu', 'john.doe@example.com', '213-436-7880'),
(166756789, 'Johny', 'Hamlton', 'john.doe@example.com', '321-466-7890'),
(987654321, 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210');

alter table transactions 
modify column transaction_timestamp varchar(30) ;


SELECT * FROM login_credentials;
ALTER TABLE login_credentials
MODIFY COLUMN username VARCHAR(45) NOT NULL;


-- UPDATE login_credentials
-- SET username = CAST(username AS CHAR(45));

INSERT INTO login_credentials (username, active_status, user_password, pin, customers_ssn)
VALUES
('JDoe', '1', 'jDoe6789','6789',123456789),
('MWu', '1', 'mW5789','5789',122345789);

SELECT * FROM accounts;
select * from login_credentials;
SELECT * FROM savings_accounts;
SELECT * FROM debit_cards;
SELECT * FROM transactions;
SELECT * FROM deposits;
SELECT * FROM customers;
SELECT * FROM beneficiaries;
SELECT * FROM withdrawals;
SELECT * FROM bank_transfers;
SELECT * FROM purchase_products;
SELECT * FROM payments;



