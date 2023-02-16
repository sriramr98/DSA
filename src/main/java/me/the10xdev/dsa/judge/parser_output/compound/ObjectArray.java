package me.the10xdev.dsa.judge.parser_output.compound;

import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;

import java.util.List;

public class ObjectArray extends ArrayOutput<JsonNode> {
    public ObjectArray(List<JsonNode> values) {
        super(values);
    }
}
