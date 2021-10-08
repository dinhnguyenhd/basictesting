package dinhnguyen.techs.testing.services;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dinhnguyen.techs.testing.entity.Author;
import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.forms.AuthorForm;
import dinhnguyen.techs.testing.repositorys.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Author> list() {
		return this.authorRepository.findAll();
	}

	@Override
	public Author getById(Long id) {
		return this.authorRepository.getById(id);
	}

	@Override
	public Author update(AuthorForm form) {
		Author author = this.modelMapper.map(form, Author.class);
		return this.authorRepository.save(author);
	}

	@Override
	public Author insert(AuthorForm form) {
		Author author = this.modelMapper.map(form, Author.class);
		return this.authorRepository.save(author);
	}

	@Override
	public List<Author> complexSearch(AuthorForm form) {

		return null;
	}

	@Override
	public Author getAuthorByName(String name) {
		// TODO Auto-generated method stub
		return this.authorRepository.getAuthorByName(name);
	}

	@Override
	public void saveMultipleBook(Set<Book> books, Author author) {
		for (Book book : books) {
			book.setAuthor(author);
		}
		author.setBooks(books);
		this.authorRepository.save(author);

	}

	@Override
	public String deleteById(Long id) {
		try {
			this.authorRepository.deleteById(id);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}

	}

}
