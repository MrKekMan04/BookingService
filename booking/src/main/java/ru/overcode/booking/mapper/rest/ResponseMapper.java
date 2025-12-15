package ru.overcode.booking.mapper.rest;

import org.springframework.stereotype.Component;
import ru.overcode.booking.exception.CodeWithMessage;
import ru.overcode.booking.web.response.ErrorDto;


@Component
public class ResponseMapper {

    public ErrorDto toError(CodeWithMessage error) {
        return new ErrorDto(error.getCode(), error.getMessage());
    }
}
