package by.auto.artur.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<IncorreсtInfo> handleExceptionUser(NoSuchUserException exception){

        IncorreсtInfo incorreсtInfo = new IncorreсtInfo();
        incorreсtInfo.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorreсtInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorreсtInfo> handleExceptionAdvertisement(NoSuchAdvertisementException exception){

        IncorreсtInfo incorreсtInfo = new IncorreсtInfo();
        incorreсtInfo.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorreсtInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorreсtInfo> handleException(Exception exception){

        IncorreсtInfo incorreсtInfo = new IncorreсtInfo();
        incorreсtInfo.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorreсtInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorreсtInfo> handleValidationExceptions(MethodArgumentNotValidException exception){

        IncorreсtInfo incorreсtInfo = new IncorreсtInfo();
        incorreсtInfo.setInfo("The form is not valid");
        return new ResponseEntity<>(incorreсtInfo, HttpStatus.BAD_REQUEST);
    }

}
