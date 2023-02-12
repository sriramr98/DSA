package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.compound.StringArray;
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
public class StringArrayParser implements ResultParser {

    @Override
    public StringArray parse(String input) throws ParsingException {

        JsonNode json;
        try {
            json = JsonParser.parse(input);
        } catch (JsonProcessingException e) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_STRING)
                    .foundType(TypeInferer.inferType(input))
                    .build();
        }


        if (!json.isArray()) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.ARRAY_STRING)
                    .foundType(IOType.OBJECT)
                    .build();
        }

        List<String> parsedStringArray = new ArrayList<>();

        for (JsonNode element : json) {

            if (!element.isValueNode() || !element.isTextual()) {
                throw ParsingException.builder()
                        .value(element.asText())
                        .expectedType(IOType.STRING)
                        .foundType(TypeInferer.inferType(input))
                        .build();
            }

            parsedStringArray.add(element.asText());
        }

        return new StringArray(parsedStringArray);
    }
}
