package dinhnguyen.techs.testing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.forms.BookForm;
import dinhnguyen.techs.testing.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/by/{id}")
	public ResponseEntity<Book> getBookById(Long id) {
		Book book = this.bookService.getById(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PostMapping("/add")
	ResponseEntity<Book> addUser(@Validated @RequestBody BookForm form) {
		Book book = this.bookService.insert(form);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

}
