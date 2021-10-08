package dinhnguyen.techs.testing.services;

import java.util.Set;

import dinhnguyen.techs.testing.entity.Author;
import dinhnguyen.techs.testing.entity.Book;
import dinhnguyen.techs.testing.forms.AuthorForm;

public interface AuthorService extends CommonService<AuthorForm, Author> {

	public Author getAuthorByName(String name);

	public void saveMultipleBook(Set<Book> book, Author author);

}
