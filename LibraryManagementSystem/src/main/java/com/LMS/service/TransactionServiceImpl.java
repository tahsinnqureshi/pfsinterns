package com.LMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.entity.Transaction;
import com.LMS.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public String saveOrUpdateTransection(Transaction transaction) {
		Long transactionId = (Long)transaction.getId();
		transactionRepository.save(transaction);
		
		if(transactionId==null) {
			return "transaction Record Saved";
		}
		else {
			return "transaction Record Updated";
		}
		
	}

	@Override
	public List<Transaction> getAllTransaction() {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction getTransaction(Long transactionId) {
		Optional<Transaction> findById = transactionRepository.findById(transactionId);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteTransaction(Long transactionId) {
		boolean existById= transactionRepository.existsById(transactionId);
		if(existById) {
			transactionRepository.deleteById(transactionId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTransactionStatus(Long id, String status) {
		try {
			transactionRepository.updateTransactionStatus(id, status);
			    return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
