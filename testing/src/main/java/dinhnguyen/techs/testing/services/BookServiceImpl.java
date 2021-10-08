package dinhnguyen.techs.testing.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.forms.BookForm;
import dinhnguyen.techs.testing.repositorys.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> list() {
		return this.bookRepository.findAll();
	}

	@Override
	public Book getById(Long id) {
		return this.bookRepository.getById(id);
	}

	@Override
	public Book update(BookForm form) {

		return null;
	}

	public Book insert(BookForm form) {
		Book entity = this.modelMapper.map(form, Book.class);
		return this.bookRepository.save(entity);
	}

	@Override
	public List<Book> complexSearch(BookForm form) {

		return null;
	}

	@Override
	public List<Book> getBookByPrice(Float price) {
		return this.bookRepository.getBookByPrice(price);
	}

	@Override
	public List<Book> getListBookByAuthor(String authorName) {
		return this.bookRepository.getListBookByAuthor(authorName);
	}

	@Override
	public String deleteById(Long id) {
		try {
			this.bookRepository.deleteById(id);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}

	}

}
