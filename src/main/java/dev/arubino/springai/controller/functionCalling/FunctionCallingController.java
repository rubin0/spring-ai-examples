package dev.arubino.springai.controller.functionCalling;

import dev.arubino.springai.service.*;
import org.springframework.ai.openai.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("function/{model}")
public class FunctionCallingController {

    private final ChatClientService chatClientService;

    public FunctionCallingController(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    @PostMapping(value = "/expense")
    public String functionCall(@PathVariable(name = "model") String model,
                               @RequestBody String userMessage) {
        return chatClientService
                .getChatClient(model)
                .prompt()
                .user(userMessage)
                .functions("expenses", "expensesByCategory", "insertExpense")
                .call()
                .content();

    }

}
