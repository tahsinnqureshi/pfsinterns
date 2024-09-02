package com.LMS.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.entity.Book;
import com.LMS.entity.Borrower;
import com.LMS.entity.Transaction;
import com.LMS.service.BookService;
import com.LMS.service.BorrowerService;
import com.LMS.service.TransactionService;




@Controller
public class LibraryController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowerService borrowerService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("book", new Book());
		return "index";
	}

	@GetMapping("/add-book")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "/add-book";
	}
	
	@GetMapping("/add-borrow")
	public String issueBook(Model model) {
		List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
		model.addAttribute("borrower", new Borrower());
		
		return "/issueBook";
	}
	
	@PostMapping("/save-book" )
	public String saveOrUpdate(@ModelAttribute("book")  Book book,
			                               Model model) {
		String msg = bookService.saveOrUpdateBook(book);
		model.addAttribute("msg", msg);
		model.addAttribute("book", new Book());
		return "add-book";
	}
	
	@PostMapping("/save-borrow")
	public String issueNewBook(@ModelAttribute("borrower") Borrower borrower,
			@ModelAttribute("transaction") Transaction transaction, Model model) {
	    // Save or update Borrower
	    String borrowerMsg = borrowerService.saveOrUpdateBorrower(borrower);
	    model.addAttribute("borrowerMsg", borrowerMsg);
	    
	    List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        Transaction newTransaction = (Transaction) addTransaction(borrower);
        
        transactionService.saveOrUpdateTransection(newTransaction);
     
        System.out.println(newTransaction.toString());
	    model.addAttribute("borrower", new Borrower());
	    return "issueBook";
	}

	
	@GetMapping("/books")
	public String getBooks(Model model) {
		List<Book> list = bookService.getAllBooks();
		model.addAttribute("books", list);
		return "view-book";
	}
	
	@GetMapping("/transaction")
	public String getTrans(Model model) {
		List<Transaction> listTr = transactionService.getAllTransaction();
		model.addAttribute("transactions", listTr);
		return "all-transaction";
	}
	
	@GetMapping("/edit")
	public String editBook(@RequestParam("id") Long id ,Model model) {
		Book book = bookService.getBook(id);
		model.addAttribute("book", book);
		return "add-book";
	}
	

	@GetMapping("/delete")
	public String deleteBook(@RequestParam("id") Long bid, Model model) {
	    bookService.deleteBook(bid);
	    model.addAttribute("msg", "Book record deleted");
	    
	    return "forward:/books";
	}
	
	public Transaction addTransaction(Borrower borrower) {
		
		Transaction transaction = new Transaction();
		
		transaction.setBookName(borrower.getBookName());	
		transaction.setBorrowerName(borrower.getName());
		transaction.setIssueDate(borrower.getIssueDate());
		transaction.setDueDate(borrower.getDueDate());
		return transaction;
	}

	@GetMapping("/delete-tr")
	public String deleteTrans(@RequestParam("id") Long tid, Model model) {
	    transactionService.deleteTransaction(tid);
	    model.addAttribute("msg", "Book record deleted");
	    
	    return "forward:/transaction";
	}
	
	@GetMapping("/updateStatus")
	public String updateUserAcc(@RequestParam("id") Long id ,
			                     @RequestParam("status") String status , Model model) {
		
		transactionService.updateTransactionStatus(id, status);
		
		if("returned".equals(status)) {
			model.addAttribute("msg","User Account Activated");
		}
		else {
			model.addAttribute("msg","User Account De-Activated");
		}
		return "forward:/transaction";
	
	}
	
}
