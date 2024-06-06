package dev.arubino.springai.controller.functionCalling.functions;

import dev.arubino.springai.controller.byod.*;
import dev.arubino.springai.controller.classification.*;
import dev.arubino.springai.controller.functionCalling.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;

@Service
public class GetExpensesByCategoryService implements Function<GetExpensesByCategoryService.Request, GetExpensesByCategoryService.Response> {

	private final ExpenseService expenseService;

    public GetExpensesByCategoryService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public record Request(Category category) {}
	public record Response(List<Expense> expenseList) {}

	public Response apply(Request request) {
		return new Response(expenseService.getExpensesByCategory(request.category));
	}
}