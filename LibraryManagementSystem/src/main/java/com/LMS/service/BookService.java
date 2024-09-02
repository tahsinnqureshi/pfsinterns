package com.LMS.service;

import java.util.List;

import com.LMS.entity.Book;

public interface BookService {

	public String saveOrUpdateBook(Book book);

	public List<Book> getAllBooks();

	public Book getBook(Long bookId);

	public boolean deleteBook(Long bookId);

	// public boolean updateUserAccStatus(Integer userId, String status);

}
