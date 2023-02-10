package me.the10xdev.dsa.models;

import me.the10xdev.dsa.types.Status;

public record TestResult(TestCase testCase, Status status, String output) {}
