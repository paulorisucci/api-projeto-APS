package livraria.imperial.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import livraria.imperial.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "livro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookEntity extends ProductEntity{

    @Column(name = "titulo")
    private String title;

    @Column(name = "autor")
    private String author;

    @Column(name = "anoLancamento")
    private Integer releaseYear;

    @Column(name="categoria")
    @Enumerated(EnumType.STRING)
    private Category category;

    @JsonIgnore
    public Example<BookEntity> getBooksBusinessKey() {
        return Example.of(this, this.getBooksBusinessKeyMatcher());
    }

    protected ExampleMatcher getBooksBusinessKeyMatcher() {
        return ExampleMatcher.matchingAny().withIgnorePaths("author", "releaseYear", "category", "id", "price",
                "quantityInStock");
    }
    protected ExampleMatcher getBooksUniqueKeyMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("author", "releaseYear", "category", "id", "price",
                "quantityInStock");
    }

    public void setCategory(String category) {
        this.category = Category.get(category);
    }

    public String getCategory() {
        return this.category.getName();
    }

    public boolean isEQualByBooksBusinesskey(BookEntity anotherBook) {
        return this.getBooksBusinessKey().equals(anotherBook.getBooksBusinessKey())
                && Objects.nonNull(anotherBook.getId()) && !Objects.equals(this.getId(), anotherBook.getId());
    }

    @JsonIgnore
    public Example<BookEntity> getBooksUniqueKey() {
        return Example.of(this, this.getBooksUniqueKeyMatcher());
    }
}
