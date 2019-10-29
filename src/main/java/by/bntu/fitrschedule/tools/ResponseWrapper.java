package by.bntu.fitrschedule.tools;

import by.bntu.fitrschedule.domain.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseWrapper {
    private static ObjectMapper jsonMapper = new ObjectMapper();

    public static ResponseDto wrap(Object object) {
        String jsonData = null;
        try {
            jsonData = jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (jsonData != null) return new ResponseDto<String>(jsonData);
        else return new ResponseDto();
    }

    public static ResponseDto wrap() {
        return new ResponseDto();
    }
}
