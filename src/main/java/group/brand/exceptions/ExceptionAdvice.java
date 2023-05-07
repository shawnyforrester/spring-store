package group.brand.exceptions;


import group.brand.messages.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {UserNotRegisteredException.class})
    public ResponseEntity<Object> handleUserRegistration(UserNotRegisteredException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserLogin(UserNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @ExceptionHandler(value = {InventoryNotFoundException.class})
    public ResponseEntity<Object> handlegetAllInventory(InventoryNotFoundException e,
                                                        WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
