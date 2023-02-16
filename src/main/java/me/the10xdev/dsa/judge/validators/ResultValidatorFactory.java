package me.the10xdev.dsa.judge.validators;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.judge.parser_output.compound.IntegerArray;
import me.the10xdev.dsa.judge.parser_output.compound.StringArray;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.types.ValidationType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class ResultValidatorFactory {

    public ResultValidator getValidator(IOType type, ValidationType validationType) {
        return switch (type) {
            case ARRAY_INT -> switch (validationType) {
                case EXACT_MATCH -> new ExactArrayValidator<IntegerArray, Integer>();
                case CONTAINS_MATCH -> new ContainsArrayValidator<IntegerArray, Integer>();
            };
            case ARRAY_STRING -> switch (validationType) {
                case EXACT_MATCH -> new ExactArrayValidator<StringArray, String>();
                case CONTAINS_MATCH -> new ContainsArrayValidator<StringArray, String>();
            };
            default -> throw new RuntimeException("Invalid Type : " + type.name());
        };
    }

}
