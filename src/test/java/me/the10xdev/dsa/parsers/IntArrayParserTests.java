package me.the10xdev.dsa.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.IntArrayParser;
import me.the10xdev.dsa.judge.parser_output.IntegerArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntArrayParserTests {

    private static IntArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new IntArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[1,2,3,4]";

        IntegerArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<Integer> expected = List.of(1,2,3,4);

        assertEquals(expected.size(), parsedValue.values().size());
        assertIterableEquals(expected, parsedValue.values());
    }

    @Test
    void throwForStringElementTypeInArray() {
        assertParsingException("[\"abc\", 4]");
    }

    @Test
    void throwForBooleanElementTypeInArray() {
        assertParsingException("[4, true]");
    }

    @Test
    void throwForStringInput() {
        assertParsingException("asb");
    }

    @Test
    void throwForBoolInput() {
        assertParsingException("true");
    }

    @Test
    void throwForIntegerInput() {
        assertParsingException("123");
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
