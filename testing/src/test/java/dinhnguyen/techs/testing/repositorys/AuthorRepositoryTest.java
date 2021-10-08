package dinhnguyen.techs.testing.repositorys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dinhnguyen.techs.testing.entity.Author;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository authorRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Author author = new Author("Jack Ma", " How to life");
		author.setName("dinhnguyen");
		author.setAddress("Hue");
		this.authorRepository.save(author);
	}

	@Test
	void testGetAuthorByName() {
		Author author = this.authorRepository.getAuthorByName("dinhnguyen");
		assertEquals(author.getName(), "dinhnguyen");

	}

}
