package com.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LMS.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}


