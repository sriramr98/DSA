package me.the10xdev.dsa.core;

import com.github.codeboy.piston4j.api.CodeFile;
import com.github.codeboy.piston4j.api.ExecutionResult;
import com.github.codeboy.piston4j.api.Piston;
import com.github.codeboy.piston4j.api.Runtime;
import me.the10xdev.dsa.models.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CodeExecutor {

    @Autowired
    private Piston piston;

    @Async
    public CompletableFuture<ExecutionResult> executeCode(UserCode userCode) {
        Runtime runtime = piston.getRuntime(userCode.getLanguage()).orElseThrow();
        CodeFile codeFile = new CodeFile(userCode.getCode());
        ExecutionResult result = runtime.execute(codeFile);
        return CompletableFuture.completedFuture(result);
    }


}
