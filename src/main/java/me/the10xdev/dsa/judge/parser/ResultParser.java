package me.the10xdev.dsa.judge.parser;

import jakarta.annotation.Nonnull;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;

public interface ResultParser {

    ParserOutput parse(String input);

}
