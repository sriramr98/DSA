package me.the10xdev.dsa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private String message;
    private List<String> errors;

}
