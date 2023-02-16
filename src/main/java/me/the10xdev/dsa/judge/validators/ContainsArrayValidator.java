package me.the10xdev.dsa.judge.validators;

import me.the10xdev.dsa.judge.parser_output.compound.ArrayOutput;

import java.util.Map;

public class ContainsArrayValidator<ArrayType extends ArrayOutput<ElementType>, ElementType> implements ResultValidator<ArrayType> {
    @Override
    public boolean validateExpectedWithActual(ArrayType expected, ArrayType actual) {

        if (expected.getValues().size() != actual.getValues().size()) return false;

        Map<ElementType, Integer> expectedElementsCount = expected.extractCountOfEachValue();
        Map<ElementType, Integer> actualElementsCount = actual.extractCountOfEachValue();

        for (Map.Entry<ElementType, Integer> expectedEntry : expectedElementsCount.entrySet()) {
            int actualElementCount = actualElementsCount.getOrDefault(expectedEntry.getKey(), -1);
            if (expectedEntry.getValue() != actualElementCount) return false;
        }

        return true;
    }
}
