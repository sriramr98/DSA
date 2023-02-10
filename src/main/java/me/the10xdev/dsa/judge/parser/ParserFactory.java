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


    public ResultParser getFactory(IOType type) {
        return switch (type) {
            case ARRAY_INT -> intArrayParser;
            case ARRAY_STRING -> stringArrayParser;
            default -> throw new RuntimeException("Invalid Type : " + type.name());
        };
    }

}
