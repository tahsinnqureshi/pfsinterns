package com.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LMS.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}


