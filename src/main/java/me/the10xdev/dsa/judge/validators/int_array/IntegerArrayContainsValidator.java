package me.the10xdev.dsa.judge.validators.int_array;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.judge.parser_output.compound.IntegerArray;
import me.the10xdev.dsa.judge.validators.ResultValidator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class IntegerArrayContainsValidator implements ResultValidator<IntegerArray> {
    @Override
    public boolean validateExpectedWithActual(IntegerArray expected, IntegerArray actual) {
        return false;
    }
}
