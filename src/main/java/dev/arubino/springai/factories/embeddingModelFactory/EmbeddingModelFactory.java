package dev.arubino.springai.factories.embeddingModelFactory;

import org.springframework.ai.embedding.*;

public interface EmbeddingModelFactory {
    EmbeddingModel createEmbeddingModel();
}