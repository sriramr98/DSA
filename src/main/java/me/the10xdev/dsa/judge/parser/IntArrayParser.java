package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.judge.parser_output.compound.IntegerArray;
import me.the10xdev.dsa.types.IOType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IntArrayParser extends ArrayParser<IntegerArray, Integer> {

    @Override
    boolean isValidType(JsonNode element) {
        return element.isInt();
    }

    @Override
    Integer parseNode(JsonNode element) {
        return element.asInt();
    }

    @Override
    IntegerArray toArray(List<Integer> values) {
        return new IntegerArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.INTEGER;
    }
}
