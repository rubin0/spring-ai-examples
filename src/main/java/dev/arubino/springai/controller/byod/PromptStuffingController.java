package dev.arubino.springai.controller.byod;

import dev.arubino.springai.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.*;

@RestController
@RequestMapping("chat/prompt/{model}")
public class PromptStuffingController {

    private final ChatService chatService;

    @Value("classpath:/documents/australian-open.txt")
    private Resource australianOpenInfo;

    public PromptStuffingController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/stuffing",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String noPromptStuffing(
            @PathVariable(name = "model") String model,
            @RequestParam(value = "userMessage", defaultValue = "Who is winner of the Australian Open 2024?") String userMessage,
            @RequestParam(value = "promptStuffing", defaultValue = "false") boolean promptStuffing
    ) throws IOException {
        String context = australianOpenInfo.getContentAsString(Charset.defaultCharset());

        String question = """
                Use the following information to answer the question at the end.
                If you don't know the answer, say "I don't know".

                {context}
                
                Question: {question}
                """;

        return chatService.chatWithText(model, u -> {
            u.text(question);
            u.param("context", promptStuffing ? context : "");
            u.param("question", userMessage);
        });
    }

}
