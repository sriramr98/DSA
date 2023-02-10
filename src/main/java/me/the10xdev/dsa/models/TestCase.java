package me.the10xdev.dsa.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    private JsonNode input;
    private String output;
    private int expectedMemory;
    private int expectedTime;

}
