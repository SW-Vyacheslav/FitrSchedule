package by.bntu.fitrschedule.domain;

public class UserDtoOut {
    private String message;

    private boolean error;

    public UserDtoOut() {
    }

    public UserDtoOut(String message) {
        this.message = message;
    }

    public UserDtoOut(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean hasError() {
        return error;
    }
}
