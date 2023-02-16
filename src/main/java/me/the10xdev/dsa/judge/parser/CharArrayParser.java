package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.compound.CharArray;
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
public class CharArrayParser extends ArrayParser<CharArray, Character> {

    @Override
    boolean isValidType(JsonNode element) {
        return TypeInferer.isChar(element.asText());
    }

    @Override
    Character parseNode(JsonNode element) {
        return element.asText().charAt(0);
    }

    @Override
    CharArray toArray(List<Character> values) {
        return new CharArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.CHAR;
    }

}
