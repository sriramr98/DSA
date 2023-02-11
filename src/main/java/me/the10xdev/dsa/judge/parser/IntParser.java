package me.the10xdev.dsa.judge.parser;

import me.the10xdev.dsa.exceptions.parse.ParsingException;
import me.the10xdev.dsa.judge.parser_output.NativeInt;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.utils.TypeInferer;
import org.springframework.stereotype.Component;

@Component
public class IntParser implements ResultParser {
    @Override
    public NativeInt parse(String input) throws ParsingException {
        try {
            return new NativeInt(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            throw ParsingException.builder()
                    .value(input)
                    .expectedType(IOType.INTEGER)
                    .foundType(TypeInferer.inferType(input))
                    .build();
        }
    }
}
