package me.the10xdev.dsa.judge.validators;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.judge.validators.int_array.IntegerArrayContainsValidator;
import me.the10xdev.dsa.judge.validators.int_array.IntegerArrayOrderedValidator;
import me.the10xdev.dsa.judge.validators.string_array.StringArrayContainsValidator;
import me.the10xdev.dsa.judge.validators.string_array.StringArrayExactValidator;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.types.ValidationType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class ResultValidatorFactory {

    private final IntegerArrayContainsValidator integerArrayContainsValidator;
    private final IntegerArrayOrderedValidator integerArrayOrderedValidator;
    private final StringArrayExactValidator stringArrayExactValidator;
    private final StringArrayContainsValidator stringArrayContainsValidator;

    public ResultValidator getValidator(IOType type, ValidationType validationType) {
        return switch (type) {
            case ARRAY_INT -> switch (validationType) {
                case EXACT_MATCH -> integerArrayOrderedValidator;
                case CONTAINS_MATCH -> integerArrayContainsValidator;
            };
            case ARRAY_STRING -> switch (validationType) {
                case EXACT_MATCH -> stringArrayExactValidator;
                case CONTAINS_MATCH -> stringArrayContainsValidator;
            };
            default -> throw new RuntimeException("Invalid Type : " + type.name());
        };
    }

}
