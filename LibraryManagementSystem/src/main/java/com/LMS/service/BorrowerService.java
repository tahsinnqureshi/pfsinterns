package com.LMS.service;

import java.util.List;

import com.LMS.entity.Borrower;


public interface BorrowerService {

	public String saveOrUpdateBorrower(Borrower borrower);

	public List<Borrower> getAllBorrower();

	public Borrower getBorrowere(Long borrowerId);

	public boolean deleteBorrower(Long borrowerId);

}
