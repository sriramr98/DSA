package me.the10xdev.dsa.judge.parsers;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.StringArrayParser;
import me.the10xdev.dsa.judge.parser_output.compound.StringArray;
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

        assertEquals(expected.size(), parsedValue.getValues().size());
        assertIterableEquals(expected, parsedValue.getValues());

    }

    @Test
    void doesNotThrowForEmptyArray() {
        String input = "[]";
        assertDoesNotThrow(() -> parser.parse(input));
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
    void throwForArrayOfObjects() {
        assertParsingException("[{\"key\": \"value\"}]");
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
