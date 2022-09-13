package ru.kata.spring.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kata.spring.rest.dto.UserIncorrectDataDto;
import ru.kata.spring.rest.exception.NoSuchUserException;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectDataDto> handleException(NoSuchUserException exception) {
        UserIncorrectDataDto data = new UserIncorrectDataDto();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectDataDto> handleException(Exception exception) {
        UserIncorrectDataDto data = new UserIncorrectDataDto();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
