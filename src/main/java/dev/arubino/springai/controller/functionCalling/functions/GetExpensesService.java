package dev.arubino.springai.controller.functionCalling.functions;

import dev.arubino.springai.controller.byod.*;
import dev.arubino.springai.controller.functionCalling.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;

@Service
public class GetExpensesService implements Function<GetExpensesService.Request, GetExpensesService.Response> {

	private final ExpenseService expenseService;

    public GetExpensesService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public record Request(String _unused) {}
	public record Response(List<Expense> expenseList) {}

	public Response apply(Request request) {
		return new Response(expenseService.getExpenses());
	}
}