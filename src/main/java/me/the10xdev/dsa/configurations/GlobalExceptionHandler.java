package me.the10xdev.dsa.configurations;

import me.the10xdev.dsa.exceptions.ApiException;
import me.the10xdev.dsa.models.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> apiExceptionHandler(ApiException exception) {
        ApiErrorResponse response = new ApiErrorResponse(exception.getMessage(), exception.getErrors());
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

}
