package livraria.imperial.book;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    private static class Messages {
        private final static String BOOK_ALREADY_EXISTS = "Já existe um livro com esse título ou um produto com esse nome";

        private final static String BOOK_NOT_FOUND = "O livro não foi encontrado";
    }

    public BookEntity create(BookEntity book) {
        verifyIfBookAlreadyExists(book);
        return save(book);
    }

    public List<BookEntity> list(BookRequest example) {
        return repository.findAll(example.getEntityExampleForSearch());
    }

    public BookEntity find(Integer idBook) {
        return searchById(idBook);
    }

    public BookEntity update(BookEntity book) {
        verifyIfBookExists(book);
        verifyIfBookAlreadyExists(book);

        return repository.save(book);
    }

    public void delete(Integer idBook) {
        repository.deleteById(idBook);
    }

    private void verifyIfBookAlreadyExists(BookEntity book) {
        Optional<BookEntity> optionalFoundBook =
                repository.findOne(book.getBooksBusinessKey());

        if(optionalFoundBook.isPresent()) {
            BookEntity foundBook = optionalFoundBook.get();

            if(book.isEQualByBooksBusinesskey(foundBook) || book.isEQualByProductssBusinesskey(foundBook)) {

                throw new EntityAlreadyExistsException(Messages.BOOK_ALREADY_EXISTS);
            }
        }
     }

    private BookEntity save(BookEntity book) {
        return repository.save(book);
    }

    private BookEntity searchById(Integer id) {
        return repository.findById(id).orElseThrow( () -> new  EntityNotFoundException(Messages.BOOK_NOT_FOUND));
    }

    private void verifyIfBookExists(BookEntity book) {
        if(!repository.existsById(book.getId())) {
            throw new EntityNotFoundException(Messages.BOOK_NOT_FOUND);
        }
    }

//    public int getAmountOfSoldBooks() {
//        repository.
//    }
}
