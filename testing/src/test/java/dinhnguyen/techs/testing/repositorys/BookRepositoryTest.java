package dinhnguyen.techs.testing.repositorys;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dinhnguyen.techs.testing.entity.Author;
import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.services.AuthorService;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorService authorService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeAll");

	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("@BeforeEach");
		Author author = new Author();
		author.setName("Jack Ma");
		author.setAddress("Hue");
		Set<Book> list = new HashSet<Book>();
		Book book1 = new Book("Java 1 ", 15.4f);
		list.add(book1);
		Book book2 = new Book("Java 2 ", 15.0f);
		list.add(book2);
		Book book3 = new Book("Java 3 ", 15f);
		list.add(book3);
		Book book4 = new Book("Java 4 ", 15f);
		list.add(book4);
		Book book5 = new Book("Java 5 ", 15.6f);
		list.add(book5);
		author.setBooks(list);
		this.authorService.saveMultipleBook(list, author);

	}

	@After
	public void teardown() {
		System.out.println(" teardown ");
	}

	@Test
	void testGetBookByPrice() {
		// Get list by price:
		List<Book> list = this.bookRepository.getBookByPrice(15.0f);
		// Make sure list book not null
		assertNotNull(list);

	}

	@Test
	void testGetListBookByAuthor() {
		// Get list from DB
		List<Book> list = this.bookRepository.getListBookByAuthor("Jack Ma");
		list.forEach(b -> System.out.println(b.getName()));
		assertNotNull(list);
	}

}
