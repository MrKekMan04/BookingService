package ru.overcode.booking.exception.error;

import ru.overcode.booking.exception.CodeWithMessage;

public class UnprocessableEntityException extends RestException {

    public UnprocessableEntityException(CodeWithMessage codeWithMessage) {
        super(codeWithMessage);
    }
}
