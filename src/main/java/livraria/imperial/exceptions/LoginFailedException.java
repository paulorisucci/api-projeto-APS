package livraria.imperial.exceptions;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
