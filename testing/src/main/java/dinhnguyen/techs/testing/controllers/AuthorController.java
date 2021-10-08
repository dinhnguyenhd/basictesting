package dinhnguyen.techs.testing.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dinhnguyen.techs.testing.entity.Author;
import dinhnguyen.techs.testing.forms.AuthorForm;
import dinhnguyen.techs.testing.services.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping("/by/{name}")
	public ResponseEntity<Author> getAuthorByName(@PathVariable("name") String name) {
		Author author = this.authorService.getAuthorByName(name);
		return new ResponseEntity<Author>(author, HttpStatus.OK);

	}

	@GetMapping("/list")
	public ResponseEntity<List<Author>> listAuthor() {
		// List<Author> list = this.authorService.list();
		List<Author> authors = new ArrayList<Author>();
		Author author1 = new Author("Dinh Nguyen", "Hue");
		Author author2 = new Author("Thuy Duong", "Ho Chi Minh");
		Author author3 = new Author("Anh Nguyen ", "Da Nang");
		authors.add(author1);
		authors.add(author2);
		authors.add(author3);
		// when(this.authorService.list()).thenReturn(authors);
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<Author> save(@RequestBody AuthorForm form) {
		Author author = this.authorService.insert(form);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<Author> update(@RequestBody AuthorForm form) {
		Author author = this.authorService.update(form);
		return new ResponseEntity<Author>(author, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") Long id) {
		// String test = this.authorService.deleteById(id);
		String test = "success";
		Map<String, Object> result = new HashMap<>();
		result.put("result", test);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

}
