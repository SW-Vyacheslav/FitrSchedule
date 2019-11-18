package by.bntu.fitrschedule.tools.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String error) {
        super(HttpStatus.NOT_FOUND, error);
    }
}
