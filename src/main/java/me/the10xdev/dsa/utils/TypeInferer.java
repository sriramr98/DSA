package me.the10xdev.dsa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.types.IOType;

import java.util.List;

public final class TypeInferer {

    private static final List<String> boolean_list = List.of("true", "false", "True", "False");


    public static IOType inferType(String input) {

        if (isFloat(input)) {
            return IOType.FLOAT;
        }

        if (isInt(input)) {
            return IOType.INTEGER;
        }

        if (isBool(input)) {
            return IOType.BOOLEAN;
        }

        if (isChar(input)) {
            return IOType.CHAR;
        }

        if (isJSONObject(input)) {
            return IOType.OBJECT;
        }

        if (isArray(input)) {
            try {
                return inferArrayType(JsonParser.parse(input));
            } catch (JsonProcessingException ignored) {}
        }

        return IOType.STRING;
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isBool(String input) {
        return boolean_list.contains(input);
    }

    public static boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return !isInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isChar(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    public static boolean isJSONObject(String input) {
        try {
            JsonNode json = JsonParser.parse(input);
            return json.isObject();
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public static boolean isArray(String input) {
        try {
            JsonNode json = JsonParser.parse(input);
            return json.isArray();
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public static IOType inferArrayType(JsonNode array) {

        if (!isArray(array.toString())) {
            // TODO: What is the ideal exception to throw?
            throw new IllegalArgumentException("Invalid Input");
        }

        if (array.isEmpty()) {
            return IOType.ARRAY;
        }

        JsonNode firstElement = array.get(0);
        IOType elementType = inferType(
               firstElement.isValueNode() ? firstElement.asText() : firstElement.toString()
        );

        int elementsCount = array.size();

        for (int i = 1; i < elementsCount; i++) {

            JsonNode element = array.get(i);

            IOType currentElementType = inferType(
                    element.isValueNode() ? element.asText() : element.toString()
            );

            // This is in case the array has multiple datatype values
            if (!currentElementType.equals(elementType)) {
                return IOType.ARRAY;
            }

        }

        return switch (elementType) {
            case BOOLEAN -> IOType.ARRAY_BOOLEAN;
            case INTEGER -> IOType.ARRAY_INT;
            case STRING -> IOType.ARRAY_STRING;
            case CHAR -> IOType.ARRAY_CHAR;
            case FLOAT -> IOType.ARRAY_FLOAT;
            case OBJECT -> IOType.ARRAY_OBJECT;
            default -> IOType.ARRAY;
        };
    }

}
