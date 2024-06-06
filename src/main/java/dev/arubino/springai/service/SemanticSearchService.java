package dev.arubino.springai.service;

import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SemanticSearchService {

    private final VectorStoreService vectorStoreService;

    public SemanticSearchService(VectorStoreService vectorStoreService) {
        this.vectorStoreService = vectorStoreService;
    }


    public List<String> semanticSearch(String model, String userMessage) {
        return vectorStoreService.similaritySearch(model, userMessage, 3);
    }
}