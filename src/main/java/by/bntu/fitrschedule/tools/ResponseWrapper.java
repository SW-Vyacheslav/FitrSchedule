package by.bntu.fitrschedule.tools;

import by.bntu.fitrschedule.domain.dto.ResponseDto;

public class ResponseWrapper {
    public static ResponseDto wrap(Object object) {
        return new ResponseDto<>(object);
    }

    public static ResponseDto wrap() {
        return new ResponseDto();
    }
}
