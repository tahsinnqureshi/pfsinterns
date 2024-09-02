package com.LMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.entity.Book;
import com.LMS.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public String saveOrUpdateBook(Book book) {
		Long bookId = (Long)book.getId();
		bookRepository.save(book);
		
		if(bookId==null) {
			return "Book Record Saved";
		}
		else {
			return "Book Record Updated";
		}
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBook(Long bookId) {
		Optional<Book> findById= bookRepository.findById(bookId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}


	@Override
	public boolean deleteBook(Long bookId) {
		boolean existById= bookRepository.existsById(bookId);
		if(existById) {
			bookRepository.deleteById(bookId);
			return true;
		}
		return false;
	}

}
