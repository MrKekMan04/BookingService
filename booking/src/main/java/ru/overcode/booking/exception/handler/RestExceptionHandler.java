package ru.overcode.booking.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.overcode.booking.exception.ExceptionMessage;
import ru.overcode.booking.exception.error.UnprocessableEntityException;
import ru.overcode.booking.mapper.rest.ResponseMapper;
import ru.overcode.booking.web.response.Response;

import java.util.List;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final ResponseMapper responseMapper;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest()
                .body(Response.fail(e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .map(exceptionMessage -> responseMapper.toError(ExceptionMessage.BAD_REQUEST
                                .withParam("param", exceptionMessage)))
                        .toList()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return ResponseEntity.badRequest()
                .body(Response.fail(e.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .map(exceptionMessage -> responseMapper.toError(ExceptionMessage.BAD_REQUEST
                                .withParam("param", exceptionMessage)))
                        .toList()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Response<Void>> handleUnprocessableEntityException(UnprocessableEntityException e) {
        return ResponseEntity.unprocessableEntity()
                .body(Response.fail(List.of(e.toError())));
    }
}
