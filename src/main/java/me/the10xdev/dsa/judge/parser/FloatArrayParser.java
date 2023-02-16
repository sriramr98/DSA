package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.compound.FloatArray;
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
public class FloatArrayParser extends ArrayParser<FloatArray, Float> {

    @Override
    boolean isValidType(JsonNode element) {
       return TypeInferer.isFloat(element.asText());
    }

    @Override
    Float parseNode(JsonNode element) {
        return Float.parseFloat(element.asText());
    }

    @Override
    FloatArray toArray(List<Float> values) {
        return new FloatArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.FLOAT;
    }
}
