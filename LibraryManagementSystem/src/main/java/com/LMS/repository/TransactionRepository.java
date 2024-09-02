package com.LMS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LMS.entity.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Modifying
	@Transactional
	@Query("update Transaction set status =:status where id=:id")
	public void updateTransactionStatus(Long id, String status);
}

