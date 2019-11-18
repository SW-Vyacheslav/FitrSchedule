package by.bntu.fitrschedule.tools.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String error;

    public ApiException(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }

    public ApiException(HttpStatus status) {
        this(status, status.getReasonPhrase());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
