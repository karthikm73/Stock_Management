package com.ofss.service;


import com.ofss.model.Transaction;
import com.ofss.repository.TransactionRepository;
import com.ofss.repository.CustomerRepository;
import com.ofss.repository.StockRepository;
import com.ofss.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
    }

    public Transaction createTransaction(Transaction transaction) {
        // Optional: validate existence of customerId and stockId
        validateCustomerAndStockExist(transaction.getCustomer().getCustomerId(), transaction.getStock().getStockId());

        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));

        validateCustomerAndStockExist(updatedTransaction.getCustomer().getCustomerId(), updatedTransaction.getStock().getStockId());

        existing.setCustomer(updatedTransaction.getCustomer());
        existing.setStock(updatedTransaction.getStock());
        existing.setTxnPrice(updatedTransaction.getTxnPrice());
        existing.setTxnType(updatedTransaction.getTxnType());
        existing.setQty(updatedTransaction.getQty());
        existing.setTxnDate(updatedTransaction.getTxnDate());

        return transactionRepository.save(existing);
    }

    public void deleteTransaction(Long id) {
        Transaction txn = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
        transactionRepository.delete(txn);
    }

    private void validateCustomerAndStockExist(Long customerId, Long stockId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }
        if (!stockRepository.existsById(stockId)) {
            throw new ResourceNotFoundException("Stock not found with ID: " + stockId);
        }
    }

    public List<Transaction> getTransactionsByCustomerId(Long customerId){
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }
        return transactionRepository.findByCustomer_CustomerId(customerId);
    }
}

