package me.the10xdev.dsa.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiException extends RuntimeException {

    private List<String> errors = new ArrayList<>();
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, HttpStatus status, List<String> errors) {
        super(message);
        this.errors = errors;
        this.status = status;
    }
}
