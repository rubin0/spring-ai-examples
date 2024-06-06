package dev.arubino.springai.controller.byod;

import dev.arubino.springai.controller.classification.*;
import dev.arubino.springai.service.*;
import org.apache.groovy.util.*;
import org.springframework.ai.document.*;
import org.springframework.ai.reader.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("structuredDataExtraction/{model}")
public class StructuredDataExtractionController {

    private final ChatClientService chatClientService;

    public StructuredDataExtractionController(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    @PostMapping(value = "/extract",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Expense extract(@PathVariable(name = "model") String model,
                          @RequestBody String userMessage) {
        return chatClientService
                .getChatClientWithDocument(model)
                .prompt()
                .user(userSpec -> userSpec.text("""
                    Extract structured data from the provided text.
                    If you do not know the value of a field asked to extract,
                    do not include any value for the field in the result.
        
                    ---------------------
                    TEXT:
                    {text}
                    ---------------------
                    """)
                        .param("text", userMessage))
                .user(userMessage)
                .call()
                .entity(Expense.class);

    }

    public record Expense(Category category, Double amount, Date date, String shortDescription) {
    }

}
