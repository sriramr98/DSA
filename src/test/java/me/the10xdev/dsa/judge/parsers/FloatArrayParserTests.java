package me.the10xdev.dsa.judge.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.FloatArrayParser;
import me.the10xdev.dsa.judge.parser_output.compound.FloatArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FloatArrayParserTests {

    private static FloatArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new FloatArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[1.2,2.4,3.5,4.5]";

        FloatArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<Float> expected = List.of(1.2f,2.4f,3.5f,4.5f);

        assertEquals(expected.size(), parsedValue.getValues().size());
        assertIterableEquals(expected, parsedValue.getValues());
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
    void throwForBooleanElementTypeInArray() {
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
