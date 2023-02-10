package me.the10xdev.dsa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiSuccessResponse {

    private String message;

}
