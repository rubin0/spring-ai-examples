package dev.arubino.springai.controller.classification;

import dev.arubino.springai.service.*;
import org.springframework.ai.embedding.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("embeddings/{model}")
public class EmbeddingsController {

    private final EmbeddingService embeddingService;

    public EmbeddingsController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @PostMapping(value = "/embed",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EmbeddingResponse embed(
            @PathVariable(name = "model") String model,
            @RequestParam(value = "userMessage") String userMessage
    ) {
        return embeddingService.embed(model, userMessage);
    }

}
