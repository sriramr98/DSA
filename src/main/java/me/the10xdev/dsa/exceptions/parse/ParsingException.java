package me.the10xdev.dsa.exceptions.parse;

import lombok.Builder;
import me.the10xdev.dsa.types.IOType;

@Builder
public class ParsingException extends Exception {

    private final String value;
    private final IOType expectedType;
    private final IOType foundType;

    @Override
    public String getMessage() {
        return String.format("Expected %s for value %s but found %s", expectedType, value ,foundType);
    }
}
