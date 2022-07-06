package livraria.imperial.book;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    private final BookService service;

    private final BookMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookEntity createBook(@RequestBody @Valid BookRequest request) {

        final var bookEntity = mapper.mapRequestToEntity(request);

        return service.create(bookEntity);
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> listBooks( BookRequest request) {

        final var bookList = service.list(request);

        return  ResponseEntity.ok(bookList);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<BookEntity> getUser(@PathVariable("idBook") Integer idBook) {
        final var foundBook = service.find(idBook);

        return ResponseEntity.ok(foundBook);
    }

    @PutMapping("/{idBook}")
    public ResponseEntity<BookEntity> update(@PathVariable("idBook")  Integer idBook,
                                             @RequestBody @Valid BookRequest request) {

        final var bookEntity = mapper.mapRequestToEntity(request);

        bookEntity.setId(idBook);

        final var savedBook = service.update(bookEntity);

        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{idBook}")
    public ResponseEntity<Void> delete(@PathVariable("idBook") Integer idBook) {

        service.delete(idBook);

        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/sold-amount")
//    public ResponseEntity<Void> getSoldAmount() {
//
//        int amount = service.soldAmount();
//
//        return ResponseEntity.noContent().build();
//    }
}
