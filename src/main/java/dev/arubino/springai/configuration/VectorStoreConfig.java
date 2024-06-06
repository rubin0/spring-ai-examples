package dev.arubino.springai.configuration;

import org.springframework.ai.vectorstore.*;
import org.springframework.context.annotation.*;

@Configuration
public class VectorStoreConfig {

    private final EmbeddingModelConfig embeddingModelConfig;

    public VectorStoreConfig(EmbeddingModelConfig embeddingModelConfig) {
        this.embeddingModelConfig = embeddingModelConfig;
    }

    @Bean(name = "ollamaVectorStore")
    SimpleVectorStore ollamaVectorStore() {
        return new SimpleVectorStore(embeddingModelConfig.getEmbeddingModel(Model.OLLAMA));
    }

    @Bean(name = "openAiVectorStore")
    SimpleVectorStore openAiVectorStore() {
        return new SimpleVectorStore(embeddingModelConfig.getEmbeddingModel(Model.OPENAI));
    }

    public SimpleVectorStore getVectorStore(String embeddingModel) {
        return switch (embeddingModel) {
            case Model.OLLAMA -> ollamaVectorStore();
            case Model.OPENAI, Model.OPENAI4 -> openAiVectorStore();
            default -> throw new IllegalArgumentException("Unknown embedding model: " + embeddingModel);
        };
    }
}