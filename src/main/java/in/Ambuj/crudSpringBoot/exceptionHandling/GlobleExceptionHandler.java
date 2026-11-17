package in.Ambuj.crudSpringBoot.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handelEmailConflictException(ConflictException ex){
        return ResponseEntity.
                status(HttpStatus.CONFLICT).
                body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handelResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException ex){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(ex.getMessage());
    }
}
