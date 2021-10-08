package dinhnguyen.techs.testing.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dinhnguyen.techs.testing.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("from Book book where book.price = ?1")
	public List<Book> getBookByPrice(@Param("price") Float price);

	@Query("from Book book where book.author.name = ?1")
	public List<Book> getListBookByAuthor(@Param("authorName") String authorName);

}
