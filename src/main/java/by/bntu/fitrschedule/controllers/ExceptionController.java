package by.bntu.fitrschedule.controllers;

import by.bntu.fitrschedule.domain.dto.ErrorDto;
import by.bntu.fitrschedule.tools.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException(ApiException e) {
        ErrorDto errorDto = new ErrorDto(
                e.getStatus().value(),
                e.getError());
        return new ResponseEntity<>(errorDto, e.getStatus());
    }
}
