package com.LMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String bookName;
    
    @Column(nullable = false)
    private String borrowerName;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate issueDate;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;


    @Column(nullable = false)
    private String status ="returned"; // e.g., "Issued", "Returned"


	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(Long id, String bookName, String borrowerName, LocalDate issueDate, LocalDate dueDate,
			String status) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.borrowerName = borrowerName;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBorrowerName() {
		return borrowerName;
	}


	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}


	public LocalDate getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", bookName=" + bookName + ", borrowerName=" + borrowerName + ", issueDate="
				+ issueDate + ", dueDate=" + dueDate + ", status=" + status + "]";
	}


    
}
