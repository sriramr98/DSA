package me.the10xdev.dsa.judge.validators;

public interface ResultValidator<T> {
    boolean validateExpectedWithActual(T expected, T actual);
}
