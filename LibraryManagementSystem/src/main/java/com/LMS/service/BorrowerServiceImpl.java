package com.LMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LMS.entity.Borrower;
import com.LMS.repository.BorrowerRepository;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepository;
	
	@Override
	public String saveOrUpdateBorrower(Borrower borrower) {
		Long borrowerId = borrower.getId();
		borrowerRepository.save(borrower);
		if(borrowerId==null) {
			return "Borrower Record Saved";
		}
		else {
			return "Borrower Record Updated";
		}
	}

	@Override
	public List<Borrower> getAllBorrower() {
		return	borrowerRepository.findAll();
	}

	@Override
	public Borrower getBorrowere(Long borrowerId) {
		Optional<Borrower> findById= borrowerRepository.findById(borrowerId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteBorrower(Long borrowerId) {
		boolean existById= borrowerRepository.existsById(borrowerId);
		if(existById) {
			borrowerRepository.deleteById(borrowerId);
			return true;
		}
		return false;
	}
	

}
