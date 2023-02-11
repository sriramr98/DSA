package me.the10xdev.dsa.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    private String inputJSON;
    private String output;
    private int expectedMemory;
    private int expectedTime;

}
