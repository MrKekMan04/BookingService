package ru.overcode.booking.exception.error;

import ru.overcode.booking.exception.CodeWithMessage;
import ru.overcode.booking.web.response.ErrorDto;

public class RestException extends RuntimeException {

    private final CodeWithMessage exception;

    public RestException(CodeWithMessage codeWithMessage) {
        this.exception = codeWithMessage;
    }

    public ErrorDto toError() {
        return new ErrorDto(
                exception.getCode(),
                exception.getMessage()
        );
    }
}
