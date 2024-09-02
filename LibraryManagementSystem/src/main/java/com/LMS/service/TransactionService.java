package com.LMS.service;

import java.util.List;

import com.LMS.entity.Transaction;

public interface TransactionService {

	public String saveOrUpdateTransection(Transaction transaction);

	public List<Transaction> getAllTransaction();

	public Transaction getTransaction(Long transactionId);

	public boolean deleteTransaction(Long transactionId);
	
	public boolean updateTransactionStatus(Long id , String status);

}
