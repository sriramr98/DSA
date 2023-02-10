package me.the10xdev.dsa.dto;

import me.the10xdev.dsa.models.TestCase;
import me.the10xdev.dsa.models.ValidationResult;

public record TestOutput(TestCase testCase, ValidationResult result) {}
