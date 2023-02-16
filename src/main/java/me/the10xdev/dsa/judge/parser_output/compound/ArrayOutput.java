package me.the10xdev.dsa.judge.parser_output.compound;

import lombok.Getter;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class ArrayOutput<T> implements ParserOutput {

    private final List<T> values;

    public ArrayOutput(List<T> values) {
       this.values = values;
    }

    public Map<T, Integer> extractCountOfEachValue() {
        Map<T, Integer> countMap = new HashMap<>();

        values.forEach(value -> {
            int count = countMap.getOrDefault(value, 0);
            countMap.put(value, count+1);
        });

        return countMap;
    }

}
