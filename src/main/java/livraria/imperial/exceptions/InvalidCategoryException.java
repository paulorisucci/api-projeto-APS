package livraria.imperial.exceptions;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException() {
        super();
    }

    public InvalidCategoryException(String message) {
        super(message);
    }
}
