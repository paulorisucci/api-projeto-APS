package livraria.imperial.errors;

import livraria.imperial.exceptions.EntityAlreadyExistsException;
import livraria.imperial.exceptions.EntityNotFoundException;
import livraria.imperial.exceptions.LoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception e) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage()), status);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailedException(Exception e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage()), status);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(Exception e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        StringWriter stringWriter = new StringWriter();

        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        return new ResponseEntity<>(
                new ErrorResponse(
                        status, e.getMessage()
                ),
                status
        );
    }
}
