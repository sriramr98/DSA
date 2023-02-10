package me.the10xdev.dsa.services;

import com.github.codeboy.piston4j.api.ExecutionOutput;
import com.github.codeboy.piston4j.api.ExecutionResult;
import lombok.AllArgsConstructor;
import me.the10xdev.dsa.core.CodeExecutor;
import me.the10xdev.dsa.judge.parser.ParserFactory;
import me.the10xdev.dsa.judge.parser.ResultParser;
import me.the10xdev.dsa.judge.parser_output.ParserOutput;
import me.the10xdev.dsa.judge.validators.ResultValidator;
import me.the10xdev.dsa.judge.validators.ResultValidatorFactory;
import me.the10xdev.dsa.models.*;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.types.Status;
import me.the10xdev.dsa.types.ValidationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class CodeExecutionService {

    private final CodeExecutor codeExecutor;
    private final ParserFactory parserFactory;
    private final ResultValidatorFactory resultValidatorFactory;

    public List<TestResult> executeAllTestCases(List<TestCase> testCases, UserCode userCode) {

        List<CompletableFuture<ExecutionResult>> allExecutions = new ArrayList<>();

        testCases.forEach(testCase -> {
            userCode.insertInput(testCase.getInput().toString());
            allExecutions.add(codeExecutor.executeCode(userCode));
        });

        //TODO: Add a max timeout
        CompletableFuture.allOf(allExecutions.toArray(new CompletableFuture[0])).join();

        return IntStream.range(0, allExecutions.size()).mapToObj(index -> {
            TestCase testCase = testCases.get(index);
            try {
                ExecutionResult result = allExecutions.get(index).get();
                ExecutionOutput output = result.getOutput();
                return new TestResult(testCase, Status.parse(output.getCode()), output.getOutput());
            } catch (InterruptedException | ExecutionException e) {
                return new TestResult(testCase, Status.FAILED, e.getMessage());
            }
        }).collect(Collectors.toList());

    }

    public ValidationResult validateTestResult(TestResult result, IOType outputType, ValidationType validationType) {

        if (result.status() == Status.FAILED) {
            return new ValidationResult(Status.FAILED, result.output());
        } else {

            ResultParser parser = parserFactory.getFactory(outputType);
            ParserOutput parsedUserOutput = parser.parse(result.output());
            ParserOutput expectedParsedOutput = parser.parse(result.testCase().getOutput());

            ResultValidator validator = resultValidatorFactory.getValidator(outputType, validationType);
            boolean isValid = validator.validateExpectedWithActual(expectedParsedOutput, parsedUserOutput);

            if (isValid) {
                return new ValidationResult(Status.FAILED, String.format("Expected %s, Got %s", result.testCase().getOutput(), result.output()));
            } else {
                return new ValidationResult(Status.SUCCESS, "Test Case Passed");
            }

        }
    }
}
