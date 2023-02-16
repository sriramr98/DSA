package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.compound.ArrayOutput;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.utils.JsonParser;
import me.the10xdev.dsa.utils.TypeInferer;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayParser<ArrayType extends ArrayOutput<ElementType>, ElementType> implements ResultParser<ArrayType> {

    @Override
    public ArrayType parse(String input) throws ParsingException {
        JsonNode json;
        try {
            json = JsonParser.parse(input);
        } catch (JsonProcessingException e) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_BOOLEAN)
                    .foundType(TypeInferer.inferType(input))
                    .build();
        }

        if (!json.isArray()) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_BOOLEAN)
                    .foundType(IOType.OBJECT)
                    .build();
        }

        List<ElementType> parsedArray = new ArrayList<>();

        for (JsonNode element : json) {
            if (!isValidType(element)) {
                throw ParsingException.builder()
                        .value(input)
                        .expectedType(getExpectedArrayElementType())
                        .foundType(TypeInferer.inferType(input))
                        .build();
            }

            parsedArray.add(parseNode(element));
        }

        return toArray(parsedArray);
    }

    abstract boolean isValidType(JsonNode element);
    abstract ElementType parseNode(JsonNode element);

    abstract ArrayType toArray(List<ElementType> values);
    abstract IOType getExpectedArrayElementType();
}
