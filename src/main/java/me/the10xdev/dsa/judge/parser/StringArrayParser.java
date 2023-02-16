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
public class StringArrayParser extends ArrayParser<StringArray, String> {

    @Override
    boolean isValidType(JsonNode element) {
        return element.isValueNode() && element.isTextual();
    }

    @Override
    String parseNode(JsonNode element) {
        return element.asText();
    }

    @Override
    StringArray toArray(List<String> values) {
        return new StringArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.STRING;
    }
}
