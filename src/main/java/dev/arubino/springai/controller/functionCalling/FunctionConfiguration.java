package dev.arubino.springai.controller.functionCalling;

import dev.arubino.springai.controller.functionCalling.functions.*;
import org.springframework.context.annotation.*;

import java.util.function.*;

@Configuration(proxyBeanMethods = false)
public class FunctionConfiguration {

    @Bean
    @Description("Get the list of expenses by the given category")
    public Function<GetExpensesByCategoryService.Request, GetExpensesByCategoryService.Response> expensesByCategory(ExpenseService expenseService) {
        return new GetExpensesByCategoryService(expenseService);
    }


    @Bean
    @Description("Get the list of expenses without a specific category")
    public Function<GetExpensesService.Request, GetExpensesService.Response> expenses(ExpenseService expenseService) {
        return new GetExpensesService(expenseService);
    }

    @Bean
    @Description("Insert a new expense")
    public Function<SaveExpensesService.Request, SaveExpensesService.Response> insertExpense(ExpenseService expenseService) {
        return new SaveExpensesService(expenseService);
    }

}
