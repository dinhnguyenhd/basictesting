package dinhnguyen.techs.testing.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dinhnguyen.techs.testing.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Query("from Author author where author.name = ?1")
	public Author getAuthorByName(@Param("name") String name);

}
