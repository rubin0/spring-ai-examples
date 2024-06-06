package dev.arubino.springai.factories.embeddingModelFactory;

import org.springframework.ai.embedding.*;
import org.springframework.ai.ollama.*;
import org.springframework.ai.ollama.api.*;

public class OllamaEmbeddingModelFactory implements EmbeddingModelFactory {

    @Override
    public EmbeddingModel createEmbeddingModel() {
        return new OllamaEmbeddingModel(new OllamaApi(),
                OllamaOptions.create()
                        .withModel("llama3"));
    }
}