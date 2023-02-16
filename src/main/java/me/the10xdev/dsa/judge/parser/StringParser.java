package me.the10xdev.dsa.judge.parser;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;
import me.the10xdev.dsa.judge.parser_output.basic.NativeString;

public class StringParser implements ResultParser {

    @Override
    public NativeString parse(String input) throws ParsingException {
        return new NativeString(input);
    }
}
