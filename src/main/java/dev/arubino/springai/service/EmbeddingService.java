package dev.arubino.springai.service;

import dev.arubino.springai.configuration.*;
import org.springframework.ai.embedding.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EmbeddingService {

    private final EmbeddingModelConfig embeddingModelConfig;

    public EmbeddingService(EmbeddingModelConfig embeddingModelConfig) {
        this.embeddingModelConfig = embeddingModelConfig;
    }

    public EmbeddingResponse embed(String model, String message) {
        var embeddingModel = embeddingModelConfig.getEmbeddingModel(model);
        return embeddingModel.embedForResponse(List.of(message));
    }

}
