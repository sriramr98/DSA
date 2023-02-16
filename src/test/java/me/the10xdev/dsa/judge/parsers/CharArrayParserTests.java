package me.the10xdev.dsa.judge.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.CharArrayParser;
import me.the10xdev.dsa.judge.parser_output.compound.CharArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharArrayParserTests {

    private static CharArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new CharArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[\"a\", \"b\"]";

        CharArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<Character> expected = List.of('a', 'b');

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
    void throwForFloatElementTypeInArray() {
        assertParsingException("[4.4, true]");
    }

    @Test
    void throwForStringInput() {
        assertParsingException("asb");
    }

    @Test
    void throwForCharInput() {
        assertParsingException("a");
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
