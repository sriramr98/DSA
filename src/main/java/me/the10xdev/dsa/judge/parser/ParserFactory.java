package me.the10xdev.dsa.judge.parser;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.types.IOType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class ParserFactory {

    private final IntArrayParser intArrayParser;
    private final StringArrayParser stringArrayParser;
    private final BooleanArrayParser booleanArrayParser;
    private final CharArrayParser charArrayParser;
    private final FloatArrayParser floatArrayParser;
    private final ObjectArrayParser objectArrayParser;


    public ResultParser getFactory(IOType type) {
        return switch (type) {
            case ARRAY_INT -> intArrayParser;
            case ARRAY_STRING -> stringArrayParser;
            case ARRAY_BOOLEAN -> booleanArrayParser;
            case ARRAY_CHAR -> charArrayParser;
            case ARRAY_FLOAT -> floatArrayParser;
            case ARRAY_OBJECT -> objectArrayParser;
            // TODO: Review exception type
            case ARRAY -> throw new RuntimeException("ARRAY not valid. Require specific ArrayType for Parsing");
            default -> throw new RuntimeException("Invalid Type : " + type.name());
        };
    }

}
