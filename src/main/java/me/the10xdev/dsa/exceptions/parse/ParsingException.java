package me.the10xdev.dsa.exceptions.parse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParsingException extends Exception {

    private final String value;
    private final String expectedType;
    private final String foundType;

    @Override
    public String getMessage() {
        return String.format("Expected %s for value %s but found %s", expectedType, value ,foundType);
    }
}
