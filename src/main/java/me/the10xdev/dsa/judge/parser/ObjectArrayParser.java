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
public class ObjectArrayParser extends ArrayParser<ObjectArray, JsonNode> {

    @Override
    boolean isValidType(JsonNode element) {
        return !element.isValueNode() && !element.isArray();
    }

    @Override
    JsonNode parseNode(JsonNode element) {
        return element;
    }

    @Override
    ObjectArray toArray(List<JsonNode> values) {
        return new ObjectArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.OBJECT;
    }

}
