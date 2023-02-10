package me.the10xdev.dsa.models;

import me.the10xdev.dsa.types.Status;

public record ValidationResult(Status status, String value) {
}
