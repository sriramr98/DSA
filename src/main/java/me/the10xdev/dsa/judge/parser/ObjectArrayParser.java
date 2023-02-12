package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.compound.ObjectArray;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.utils.JsonParser;
import me.the10xdev.dsa.utils.TypeInferer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ObjectArrayParser implements ResultParser {

    @Override
    public ObjectArray parse(String input) throws ParsingException {

        JsonNode json;
        try {
            json = JsonParser.parse(input);
        } catch (JsonProcessingException e) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_OBJECT)
                    .foundType(TypeInferer.inferType(input))
                    .build();
        }

        if (!json.isArray()) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_OBJECT)
                    .foundType(IOType.OBJECT)
                    .build();
        }

        List<JsonNode> parsedObjectArray = new ArrayList<>();

        for (JsonNode element : json) {
            if (element.isValueNode() || element.isArray()) {
                throw ParsingException.builder()
                        .value(input)
                        .expectedType(IOType.BOOLEAN)
                        .foundType(TypeInferer.inferType(input))
                        .build();
            }

            try {
                parsedObjectArray.add(JsonParser.parse(element.toString()));
            } catch (JsonProcessingException ex) {
                throw ParsingException.builder()
                        .value(element.toString())
                        .expectedType(IOType.OBJECT)
                        .foundType(TypeInferer.inferType(element.toString()))
                        .build();
            }

        }

        return new ObjectArray(parsedObjectArray);

    }

}
