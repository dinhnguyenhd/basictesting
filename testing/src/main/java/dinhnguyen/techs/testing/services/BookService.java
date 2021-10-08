package dinhnguyen.techs.testing.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.forms.BookForm;

public interface BookService extends CommonService<BookForm, Book> {

	public List<Book> getBookByPrice(@Param("price") Float price);

	public List<Book> getListBookByAuthor(String authorName);

}
