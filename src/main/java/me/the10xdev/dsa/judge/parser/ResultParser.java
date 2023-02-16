package me.the10xdev.dsa.judge.parser;

import me.the10xdev.dsa.exceptions.parse.ParsingException;

public interface ResultParser<ParseOutputType> {

    ParseOutputType parse(String input) throws ParsingException;

}
