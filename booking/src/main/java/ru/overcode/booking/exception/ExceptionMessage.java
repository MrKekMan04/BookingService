package ru.overcode.booking.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public enum ExceptionMessage implements CodeWithMessage {

    SEAT_NOT_FOUND("Стол не найден"),
    OFFICE_FLOOR_NOT_FOUND("Офис не найден"),
    CLIENT_HAS_FIXED_SEAT("В офисе есть закрепленное место"),
    CLIENT_HAS_ACTIVE_BOOKING("Есть забронированное место"),
    SEAT_ALREADY_BOOKED("Есть забронированное место"),
    BAD_REQUEST("{param}"),
    INTERNAL_SERVER("Произошла непредвиденная ошибка");

    private final String message;
    private final Map<String, String> params = new HashMap<>();

    ExceptionMessage(String message) {
        this.message = message;
    }

    public ExceptionMessage withParam(String param, String value) {
        this.params.put(param, value);
        return this;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        AtomicReference<String> finalMessage = new AtomicReference<>(this.message);
        params.forEach((param, value) -> finalMessage.set(finalMessage.get().replace("{" + param + "}", value)));
        return finalMessage.get();
    }
}
