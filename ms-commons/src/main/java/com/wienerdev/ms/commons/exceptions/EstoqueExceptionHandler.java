package com.wienerdev.ms.commons.exceptions;

import com.wienerdev.ms.commons.dto.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class EstoqueExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EstoqueException.class)
    public ResponseEntity<Object> handleNotFoundException(EstoqueException ex,
                                                          WebRequest request) {
        CustomErrorResponse customErrorResponse =
                CustomErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(ex.getLocalizedMessage())
                        .errors(List.of(ex.getLocalizedMessage()))
                        .build();

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstoqueException.class)
    public ResponseEntity<Object> handleBadRequestException(EstoqueException ex,
                                                            WebRequest request) {
        CustomErrorResponse customErrorResponse =
                CustomErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getLocalizedMessage())
                        .errors(List.of(ex.getLocalizedMessage()))
                        .build();

        return ResponseEntity.badRequest().body(customErrorResponse);
    }

    @ExceptionHandler(EstoqueException.class)
    public ResponseEntity<Object> handleExceptionInternal(EstoqueException ex,
                                                          WebRequest request) {
        CustomErrorResponse customErrorResponse =
                CustomErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message(ex.getLocalizedMessage())
                        .errors(List.of(ex.getLocalizedMessage()))
                        .build();

        return new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
    }
}
