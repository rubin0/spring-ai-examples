package dev.arubino.springai.controller.byod;

import dev.arubino.springai.controller.classification.*;

import java.time.*;

public record Expense(Category category, LocalDate date, int amount, String shortDescription) {
}
