package dev.arubino.springai.controller.classification;

import dev.arubino.springai.service.*;
import org.springframework.ai.document.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("semanticSearch/{model}")
public class SemanticSearchController {

    private final SemanticSearchService semanticSearchService;
    private final VectorStoreService vectorStoreService;

    public SemanticSearchController(SemanticSearchService semanticSearchService, VectorStoreService vectorStoreService) {
        this.semanticSearchService = semanticSearchService;
        this.vectorStoreService = vectorStoreService;
    }

    @PostMapping(value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> semanticSearch(
            @PathVariable(name = "model") String model,
            @RequestBody String userMessage
    ) {
        return semanticSearchService.semanticSearch(model, userMessage);
    }

    @PostMapping(value = "/load")
    public String load(@PathVariable(name = "model") String model) {
        var data = List.of(
                "Eiffel Tower, Paris: Iconic iron structure offering panoramic city views from its observation decks.",
                "Great Wall of China: Ancient fortification stretching across northern China, renowned for its historical significance and stunning scenery.",
                "Machu Picchu, Peru: Incan citadel set high in the Andes Mountains, known for its archaeological significance and breathtaking views.",
                "Colosseum, Rome: Ancient amphitheater in the heart of Rome, famous for its gladiatorial contests and architectural grandeur.",
                "Taj Mahal, India: Majestic white marble mausoleum in Agra, celebrated as a symbol of love and Mughal architecture.",
                "Statue of Liberty, New York: Iconic statue symbolizing freedom, located on Liberty Island in New York Harbor.",
                "Santorini, Greece: Picturesque island known for its white-washed buildings, blue-domed churches, and stunning sunsets.",
                "Sydney Opera House, Australia: Architectural masterpiece and cultural hub on Sydney’s harbor, famed for its distinctive sail-like design.",
                "Great Barrier Reef, Australia: World's largest coral reef system, renowned for its vibrant marine life and clear blue waters.",
                "Pyramids of Giza, Egypt: Ancient pyramid structures near Cairo, celebrated as monumental feats of engineering and one of the Seven Wonders of the Ancient World."
        );

        List<Document> documents = data.stream()
                .map(Document::new)
                .toList();

        vectorStoreService.loadDocument(model, documents);
        return "Documents loaded!";
    }

}
