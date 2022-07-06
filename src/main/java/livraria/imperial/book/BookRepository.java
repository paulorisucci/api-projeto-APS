package livraria.imperial.book;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {


    @Query(value =
            "SELECT COUNT(*) FROM BookEntity " +
            "JOIN purchase"
            , nativeQuery = true)
    long countSoldBooks();
}
