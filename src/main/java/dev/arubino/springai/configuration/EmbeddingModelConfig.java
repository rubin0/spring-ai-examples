package dev.arubino.springai.configuration;

import dev.arubino.springai.factories.embeddingModelFactory.*;
import org.springframework.ai.embedding.*;
import org.springframework.context.annotation.*;

@Configuration
public class EmbeddingModelConfig {

    @Bean
    public OllamaEmbeddingModelFactory ollamaEmbeddingModelFactory() {
        return new OllamaEmbeddingModelFactory();
    }

    @Bean
    public OpenAiEmbeddingModelFactory openAiEmbeddingModelFactory() {
        return new OpenAiEmbeddingModelFactory();
    }

    public EmbeddingModel getEmbeddingModel(String embeddingModel) {
        return switch (embeddingModel) {
            case Model.OLLAMA -> ollamaEmbeddingModelFactory().createEmbeddingModel();
            case Model.OPENAI -> openAiEmbeddingModelFactory().createEmbeddingModel();
            default -> throw new IllegalArgumentException("Unknown embedding model: " + embeddingModel);
        };
    }
}