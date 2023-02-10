package me.the10xdev.dsa.controllers;

import lombok.AllArgsConstructor;
import me.the10xdev.dsa.dto.RunCodeDTO;
import me.the10xdev.dsa.dto.TestOutput;
import me.the10xdev.dsa.entities.Problem;
import me.the10xdev.dsa.models.TestResult;
import me.the10xdev.dsa.models.UserCode;
import me.the10xdev.dsa.models.ValidationResult;
import me.the10xdev.dsa.services.CodeExecutionService;
import me.the10xdev.dsa.services.ProblemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
@AllArgsConstructor
public class CodeController {

    private CodeExecutionService codeExecutionService;
    private ProblemService problemService;

    @PostMapping("/test")
    public List<TestOutput> runTestCode(@RequestBody RunCodeDTO codeInput) throws Exception {
        //TODO: Get problem details from problemId<
        Problem problem = problemService.getProblem(codeInput.problemId()).orElseThrow();

        UserCode userCode = new UserCode(codeInput.code(), codeInput.language());
        userCode.mergeWithStub(problem.getCodeRunStub());

        List<TestResult> results = codeExecutionService.executeAllTestCases(problem.getRunTestCases(), userCode);

        return results.stream().map(result -> {
            ValidationResult validationResult =  codeExecutionService.validateTestResult(result, problem.getOutputType(), problem.getValidationType());
            return new TestOutput(result.testCase(), validationResult);
        }).toList();
    }

    @PostMapping("/submit")
    public void submitCode() {

    }

    @GetMapping("/runtimes")
    public void getSupportedRuntimes() {

    }

}
