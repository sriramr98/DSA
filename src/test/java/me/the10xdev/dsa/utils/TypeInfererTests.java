package me.the10xdev.dsa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.the10xdev.dsa.types.IOType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeInfererTests {

    private static ObjectMapper mapper;

    @BeforeAll
    static void init() {
       mapper = new ObjectMapper();
    }

    //region Int Inference Tests
    @Test
    void isIntReturnsTrueForValidInt() {
        assertTrue(TypeInferer.isInt("123"));
    }

    @Test
    void isIntReturnsFalseForString() {
        assertFalse(TypeInferer.isInt("abc"));
    }

    @Test
    void isIntReturnsFalseForFloat() {
        assertFalse(TypeInferer.isInt("12.23"));
    }
    //endregion

    //region Boolean Inference Tests
    @Test
    void isBoolReturnsTrueForValidBool() {
        assertTrue(TypeInferer.isBool("true"));
        assertTrue(TypeInferer.isBool("false"));
        assertTrue(TypeInferer.isBool("True"));
        assertTrue(TypeInferer.isBool("False"));
    }

    @Test
    void isBoolReturnsFalseForCaseMismatch() {
        assertFalse(TypeInferer.isBool("tRue"));
        assertFalse(TypeInferer.isBool("faLSe"));
    }

    @Test
    void isBoolReturnsFalseForNonBooleanString() {
        assertFalse(TypeInferer.isBool("Abcd"));
    }

    @Test
    void isBoolReturnsFalseForInt() {
        assertFalse(TypeInferer.isBool("1234"));
    }
    //endregion

    //region Float Inference Tests
    @Test
    void isFloatReturnsTrueForValidFloat() {
        assertTrue(TypeInferer.isFloat("12.123"));
    }

    @Test
    void isFloatReturnsFalseForInt() {
        assertFalse(TypeInferer.isFloat("1234"));
    }

    @Test
    void isFloatReturnsFalseForString() {
        assertFalse(TypeInferer.isFloat("abcd"));
    }

    @Test
    void isFloatReturnsFalseForBool() {
        assertFalse(TypeInferer.isFloat("true"));
    }

    //endregion

    //region Char Inference Tests
    @Test
    void isCharReturnsTrueForValidInput() {
        assertTrue(TypeInferer.isChar("a"));
    }

    @Test
    void isCharReturnsFalseForString() {
        assertFalse(TypeInferer.isChar("abc"));
    }

    @Test
    void isCharReturnsFalseForNumber() {
        assertFalse(TypeInferer.isChar("1"));
    }

    @Test
    void isCharReturnsFalseForSymbols() {
        assertFalse(TypeInferer.isChar("%"));
    }
    //endregion

    //region JSON Object Inference Tests

    @Test
    void isJSONObjectReturnsTrueForValidInput() {
        assertTrue(TypeInferer.isJSONObject("{\"abc\": \"def\"}"));
    }

    @Test
    void isJSONObjectReturnsFalseForIncorrectJSONSyntax() {
        assertFalse(TypeInferer.isJSONObject("{\"abc\" \"def\"}"));
        assertFalse(TypeInferer.isJSONObject("{\"abc\" : \"def\""));
    }

    @Test
    void isJSONObjectReturnsFalseForArray() {
        assertFalse(TypeInferer.isJSONObject("[1,2,4]"));
    }

    @Test
    void isJSONObjectForString() {
        assertFalse(TypeInferer.isJSONObject("abcd"));
    }

    //endregion

    //region Array Inference Tests

    @Test
    void isArrayReturnsTrueForValidInput() {
        assertTrue(TypeInferer.isArray("[1,2,3]"));
    }

    @Test
    void isArrayReturnsFalseForJSONObject() {
        assertFalse(TypeInferer.isArray("{\"key\": \"value\"}"));
    }

    @Test
    void isArrayReturnsFalseForIncorrectArraySyntax() {
        assertFalse(TypeInferer.isArray("[2,2,4"));
    }

    @Test
    void isArrayReturnsFalseForString() {
        assertFalse(TypeInferer.isArray("abcd"));
    }

    //endregion

    //region inferType Tests
    @Test
    void inferTypeReturnsFloatForFloatInput() {
        assertEquals(IOType.FLOAT, TypeInferer.inferType("13.24"));
    }

    @Test
    void inferTypeReturnIntForIntInput() {
        assertEquals(IOType.INTEGER, TypeInferer.inferType("1234"));
    }

    @Test
    void inferTypeReturnsBoolForBoolInput() {
        assertEquals(IOType.BOOLEAN, TypeInferer.inferType("true"));
        assertEquals(IOType.BOOLEAN, TypeInferer.inferType("false"));
        assertEquals(IOType.BOOLEAN, TypeInferer.inferType("True"));
        assertEquals(IOType.BOOLEAN, TypeInferer.inferType("False"));
    }

    @Test
    void inferTypeReturnsCharForCharInput() {
        assertEquals(IOType.CHAR, TypeInferer.inferType("c"));
    }

    @Test
    void inferTypeReturnsObjectForJSONObjectInput() {
        assertEquals(IOType.OBJECT, TypeInferer.inferType("{\"key\": \"value\"}"));
    }

    @Test
    void inferTypeReturnsArrayForArrayInput() {
        assertEquals(IOType.ARRAY_INT, TypeInferer.inferType("[1,2, 3]"));
        assertEquals(IOType.ARRAY_STRING, TypeInferer.inferType("[\"abc\", \"def\"]"));
        assertEquals(IOType.ARRAY_BOOLEAN, TypeInferer.inferType("[true, false]"));
        assertEquals(IOType.ARRAY_CHAR, TypeInferer.inferType("[\"a\", \"b\"]"));
        assertEquals(IOType.ARRAY_FLOAT, TypeInferer.inferType("[12.34, 23.23]"));
        assertEquals(IOType.ARRAY_OBJECT, TypeInferer.inferType("[ {\"key\": \"value\"}, {\"key\": \"value\"} ]"));
        assertEquals(IOType.ARRAY, TypeInferer.inferType("[]"));
    }

    //endregion

    //region Array SubType Inference Tests

    @Test
    void returnsCorrectTypeForArray() throws JsonProcessingException {
        assertEquals(IOType.ARRAY_INT, TypeInferer.inferArrayType(mapper.readTree("[1,2,4]")));
        assertEquals(IOType.ARRAY_CHAR, TypeInferer.inferArrayType(mapper.readTree("[\"a\", \"b\"]")));
        assertEquals(IOType.ARRAY_BOOLEAN, TypeInferer.inferArrayType(mapper.readTree("[true, false]")));
        assertEquals(IOType.ARRAY_STRING, TypeInferer.inferArrayType(mapper.readTree("[\"abc\", \"def\"]")));
        assertEquals(IOType.ARRAY_FLOAT, TypeInferer.inferArrayType(mapper.readTree("[12.34, 12.45]")));
        assertEquals(IOType.ARRAY_OBJECT, TypeInferer.inferArrayType(mapper.readTree("[{\"key\": \"value\"}]")));
        assertEquals(IOType.ARRAY, TypeInferer.inferArrayType(mapper.readTree("[1, 2, 1.2, true]")));
    }

    @Test
    void throwsForJSONObject() {
        assertThrows(
                RuntimeException.class,
                () -> TypeInferer.inferArrayType(mapper.readTree("{\"key\": \"value\"}"))
        );
    }

    //endregion
}
