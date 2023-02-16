package me.the10xdev.dsa.judge.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser.BooleanArrayParser;
import me.the10xdev.dsa.judge.parser.ObjectArrayParser;
import me.the10xdev.dsa.judge.parser_output.compound.ObjectArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectArrayParserTests {

    private static ObjectArrayParser parser;

    @BeforeAll
    static void setupParser() {
        parser = new ObjectArrayParser();
    }

    @Test
    void returnsParsedArrayForValidInput() {

        String validInput = "[{\"key\": \"value\"}, {\"key\": \"value\"}]";

        ObjectArray parsedValue = assertDoesNotThrow(() -> parser.parse(validInput));

        List<String> expectedList = List.of("{\"key\":\"value\"}", "{\"key\":\"value\"}");
        List<String> actualList = parsedValue.getValues().stream().map(JsonNode::toString).toList();

        assertEquals(expectedList.size(), actualList.size());
        assertIterableEquals(expectedList, actualList);

    }

    @Test
    void doesNotThrowForEmptyArray() {
        String input = "[]";
        assertDoesNotThrow(() -> parser.parse(input));
    }

    @Test
    void doesNotThrowForEmptyObjectInArray() {
        String input = "[{}]";
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
