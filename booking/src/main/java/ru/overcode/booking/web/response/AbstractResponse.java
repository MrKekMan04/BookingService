package ru.overcode.booking.web.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class AbstractResponse<T> {

    private T data;
    private List<ErrorDto> errors = new ArrayList<>();
}
