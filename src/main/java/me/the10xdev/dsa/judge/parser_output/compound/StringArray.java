package me.the10xdev.dsa.judge.parser_output.compound;

import me.the10xdev.dsa.judge.parser_output.ParserOutput;

import java.util.List;

public class StringArray extends ArrayOutput<String> {
    public StringArray(List<String> values) {
        super(values);
    }
}