package me.the10xdev.dsa.judge.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.IntParser;
import me.the10xdev.dsa.judge.parser_output.basic.NativeInt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntParserTests {

    private static IntParser parser;

    @BeforeAll
    static void initParser() {
        parser = new IntParser();
    }

    @Test
    void parseValidIntInput() {
        String input = "124";

        NativeInt parsedValue = assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(124, parsedValue.value());

    }

    @Test
    void throwsForStringInput() {
        assertParsingException("12s");
    }

    @Test
    void throwsForFloat() {
        assertParsingException("12.42");
    }

    @Test
    void throwsForArray() {
        assertParsingException("[1,2,3]");
    }

    @Test
    void throwsForObject() {
        assertParsingException("{\"key\": \"value\"}");
    }

    private void assertParsingException(String input) {
        assertThrows(
                ParsingException.class,
                () -> parser.parse(input)
        );
    }

}
