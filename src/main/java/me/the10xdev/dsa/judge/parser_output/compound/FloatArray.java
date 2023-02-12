package me.the10xdev.dsa.judge.parser_output.compound;

import lombok.Getter;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;

import java.util.List;

public record FloatArray(List<Float> values) implements ParserOutput {
}
