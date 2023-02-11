package me.the10xdev.dsa.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.StringArrayParser;
import me.the10xdev.dsa.judge.parser_output.StringArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringArrayParserTests {

    private static StringArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new StringArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[\"abc\", \"def\"]";

        StringArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<String> expected = List.of("abc", "def");

        assertEquals(expected.size(), parsedValue.value().size());
        assertIterableEquals(expected, parsedValue.value());

    }

    @Test
    void throwForIntElementInArray() {
        assertParsingException("[\"abc\", 5]");
    }

    @Test
    void throwForBooleanElementInArray() {
        assertParsingException("[\"abc\", true]");
    }

    @Test
    void throwForStringInput() {
        assertParsingException("abc");
    }

    @Test
    void throwForIntegerInput() {
        assertParsingException("1235");
    }

    @Test
    void throwForJSONObjectInput() {
        assertParsingException("{\"key\": \"value\"}");
    }

    private void assertParsingException(String input) {
        assertThrows(
                ParsingException.class,
                () -> parser.parse(input)
        );
    }

}
