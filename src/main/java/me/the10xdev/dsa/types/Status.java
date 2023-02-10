package me.the10xdev.dsa.types;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {

    SUCCESS(0),
    FAILED(1);

    private final int status;

    Status(int status) {
       this.status = status;
    }

    public static Status parse(int status) {
        return Arrays.stream(Status.values()).filter(executionStatus -> executionStatus.getStatus() == status).findFirst().orElseThrow();
    }
}
