package me.the10xdev.dsa.judge.validators.string_array;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.judge.parser_output.StringArray;
import me.the10xdev.dsa.judge.validators.ResultValidator;
import me.the10xdev.dsa.types.ValidationType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class StringArrayExactValidator implements ResultValidator<StringArray> {
    @Override
    public boolean validateExpectedWithActual(StringArray expected, StringArray actual) {
        return false;
    }
}
