package me.the10xdev.dsa.configurations;

import com.github.codeboy.piston4j.api.Piston;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeExecutionConfig {

    @Bean
    public Piston piston() {
        return Piston.getDefaultApi();
    }

}
