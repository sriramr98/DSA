package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.internal.JacksonUtil;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.IntegerArray;
import me.the10xdev.dsa.utils.JsonParser;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IntArrayParser  implements ResultParser {
    @Override
    public IntegerArray parse(String input) throws ParsingException {

        JsonNode json;
        try {
            json = JsonParser.parse(input);
        } catch (JsonProcessingException e) {
            throw new ParsingException(input, "Array", "String");
        }


        if (!json.isArray()) {
            throw new ParsingException(input, "Array" ,json.getNodeType().toString());
        }

        List<Integer> parsedIntArray = new ArrayList<>();

        for (JsonNode element : json) {
            if (!element.isInt()) {
                throw new ParsingException(element.asText(),"Integer", element.getNodeType().toString());
            }

            parsedIntArray.add(element.asInt());
        }

        return new IntegerArray(parsedIntArray);
    }
}
