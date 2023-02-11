package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.StringArray;
import me.the10xdev.dsa.utils.JsonParser;
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
            throw new ParsingException(input, "Array", "String");
        }


        if (!json.isArray()) {
            throw new ParsingException(input, "Array" ,json.getNodeType().toString());
        }

        List<String> parsedStringArray = new ArrayList<>();

        for (JsonNode element : json) {

            if (!element.isTextual()) {
                throw new ParsingException(element.asText(), "String", element.getNodeType().toString());
            }

            parsedStringArray.add(element.asText());
        }

        return new StringArray(parsedStringArray);
    }
}
