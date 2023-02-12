package me.the10xdev.dsa.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.BooleanArrayParser;
import me.the10xdev.dsa.judge.parser_output.compound.BooleanArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BooleanArrayParserTests {

    private static BooleanArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new BooleanArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[true, false]";

        BooleanArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<Boolean> expected = List.of(true, false);

        assertEquals(expected.size(), parsedValue.values().size());
        assertIterableEquals(expected, parsedValue.values());
    }

    @Test
    void doesNotThrowForEmptyArray() {
        String input = "[]";
        assertDoesNotThrow(() -> parser.parse(input));
    }

    @Test
    void throwsForIntegerArray() {
        assertParsingException("[2,4]");
    }

    @Test
    void throwForStringElementTypeInArray() {
        assertParsingException("[\"abc\", 4]");
    }

    @Test
    void throwForFloatElementTypeInArray() {
        assertParsingException("[4.4, true]");
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
    void throwForFloatInput() {
        assertParsingException("123.423");
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
