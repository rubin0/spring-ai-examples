package dev.arubino.springai.controller.classification;

import dev.arubino.springai.service.*;
import org.springframework.ai.embedding.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classification/{model}")
public class ClassificationController {

    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }


    @PostMapping(value = "/classify",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Category classify(
            @PathVariable(name = "model") String model,
            @RequestBody String userMessage
    ) {
        var examples = """
                Input: Tickets history museum
                Output: "ENTERTAINMENT"
                
                Input: Electricity bill
                Output: "UTILITIES"
                
                Input: New beautiful couch
                Output: "HOME"
                
                Input: Burger and fries
                Output: "FOOD"
                
                Input: Fancy dinner with parents
                Output: "FOOD"
                
                Input: July home rent
                Output: "HOME"
                """;

        return classificationService.classify(model, userMessage, examples, Category.class);
    }

}
