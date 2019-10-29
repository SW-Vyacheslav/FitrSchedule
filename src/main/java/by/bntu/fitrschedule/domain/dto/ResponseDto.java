package by.bntu.fitrschedule.domain.dto;

public class ResponseDto<T> {
    private Long status;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(T data) {
        this.data = data;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
