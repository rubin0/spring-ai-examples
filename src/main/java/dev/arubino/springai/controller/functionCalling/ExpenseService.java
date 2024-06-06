package dev.arubino.springai.controller.functionCalling;

import dev.arubino.springai.controller.byod.*;
import dev.arubino.springai.controller.classification.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

@Service
public class ExpenseService {

    private static final Map<Integer, Expense> expenses = new ConcurrentHashMap<>();
    private static final AtomicInteger id = new AtomicInteger(1);

    static {
        expenses.put(id.getAndIncrement(), new Expense(Category.FOOD, LocalDate.of(2024, 6, 1), 50, "Mexican dinner"));
        expenses.put(id.getAndIncrement(), new Expense(Category.HOME, LocalDate.of(2024, 6, 4), 12, "Lightbulbs"));
        expenses.put(id.getAndIncrement(), new Expense(Category.UTILITIES, LocalDate.of(2024, 6, 7), 32, "Mobile data"));
        expenses.put(id.getAndIncrement(), new Expense(Category.FOOD, LocalDate.of(2024, 6, 8), 8, "Kebab"));
        expenses.put(id.getAndIncrement(), new Expense(Category.OTHER, LocalDate.of(2024, 6, 8), 50, "Gift"));
    }

    public Expense saveExpense(Expense expense) {
        return expenses.put(id.getAndIncrement(), expense);
    }

    public List<Expense> getExpensesByCategory(Category category) {
        return expenses.values().stream()
                .filter(expense -> expense.category().equals(category))
                .toList();
    }

    public List<Expense> getExpenses() {
        return expenses.values().stream().toList();
    }
}
