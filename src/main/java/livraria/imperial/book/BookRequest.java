package livraria.imperial.book;

import livraria.imperial.book.validation.CategoryValidation;
import livraria.imperial.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest extends ProductRequest {

    @NotNull(message = "{book.title.notnull}")
    @Size(max = 100, message = "{book.title.size}")
    private String title;

    @Size(max = 100, message = "{book.author.size}")
    private String author;

    private Integer releaseYear;

    @CategoryValidation(enumClass = Category.class)
    private String category;

    public Example<BookEntity> getEntityExampleForSearch() {

        BookEntity entityForSearch = new BookEntity();

        if(Objects.nonNull(title)) {
            entityForSearch.setTitle(title);
        }

        if(Objects.nonNull(author)) {
            entityForSearch.setAuthor(author);
        }

        if(Objects.nonNull(releaseYear)) {
            entityForSearch.setReleaseYear(releaseYear);
        }

        if(Objects.nonNull(category)) {
            entityForSearch.setCategory(category);
        }

        if(Objects.nonNull(price)) {
            entityForSearch.setPrice(price);
        }

        if(Objects.nonNull(quantityInStock)) {
            entityForSearch.setQuantityInStock(quantityInStock);
        }

        if(Objects.nonNull(name)) {
            entityForSearch.setName(name);
        }

        return entityForSearch.getBooksUniqueKey();
    }

    public ExampleMatcher getMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("id", "name", "price", "quantityInStock");
    }
}
