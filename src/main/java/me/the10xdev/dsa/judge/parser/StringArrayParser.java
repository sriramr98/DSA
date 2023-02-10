package me.the10xdev.dsa.judge.parser;

import me.the10xdev.dsa.judge.parser_output.StringArray;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StringArrayParser implements ResultParser {

    @Override
    public StringArray parse(String input) {
        // TODO: Logic to convert string input => array of string

        return new StringArray();
    }
}
