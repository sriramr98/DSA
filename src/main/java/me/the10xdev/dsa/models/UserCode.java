package me.the10xdev.dsa.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCode {
    private String code;
    private String language;

    public void mergeWithStub(String stub) {
        this.code = stub.replaceFirst("<user_code>", this.code);
    }

    public void insertInput(String input) {
        this.code = this.code.replaceFirst("<input>", input);
    }

}
