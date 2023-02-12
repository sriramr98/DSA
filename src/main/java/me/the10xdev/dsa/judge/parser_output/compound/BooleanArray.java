package me.the10xdev.dsa.judge.parser_output.compound;

import me.the10xdev.dsa.judge.parser_output.ParserOutput;

import java.util.List;

public record BooleanArray(List<Boolean> values) implements ParserOutput {
}
