package me.the10xdev.dsa.judge.parser;

import me.the10xdev.dsa.judge.parser_output.IntegerArray;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IntArrayParser implements ResultParser {
    @Override
    public IntegerArray parse(String input) {
        return new IntegerArray();
    }
}
