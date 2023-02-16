package me.the10xdev.dsa.judge.parser;

import com.fasterxml.jackson.databind.JsonNode;
import me.the10xdev.dsa.judge.parser_output.compound.BooleanArray;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.utils.TypeInferer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BooleanArrayParser extends ArrayParser<BooleanArray, Boolean> {

    @Override
    boolean isValidType(JsonNode element) {
        return TypeInferer.isBool(element.asText());
    }

    @Override
    Boolean parseNode(JsonNode element) {
        return element.asBoolean();
    }

    @Override
    BooleanArray toArray(List<Boolean> values) {
        return new BooleanArray(values);
    }

    @Override
    IOType getExpectedArrayElementType() {
        return IOType.BOOLEAN;
    }

}
