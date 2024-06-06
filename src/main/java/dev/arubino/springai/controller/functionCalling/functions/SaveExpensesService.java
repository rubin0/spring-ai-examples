package dev.arubino.springai.controller.functionCalling.functions;

import dev.arubino.springai.controller.byod.*;
import dev.arubino.springai.controller.functionCalling.*;
import org.springframework.stereotype.*;

import java.util.function.*;

@Service
public class SaveExpensesService implements Function<SaveExpensesService.Request, SaveExpensesService.Response> {

	private final ExpenseService expenseService;

    public SaveExpensesService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public record Request(Expense expense) {}
	public record Response(Expense expense) {}

	public Response apply(Request request) {
		return new Response(expenseService.saveExpense(request.expense));
	}
}