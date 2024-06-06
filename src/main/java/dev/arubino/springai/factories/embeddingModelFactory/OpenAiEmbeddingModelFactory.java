package dev.arubino.springai.factories.embeddingModelFactory;

import org.springframework.ai.document.*;
import org.springframework.ai.embedding.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;
import org.springframework.ai.retry.*;

public class OpenAiEmbeddingModelFactory implements EmbeddingModelFactory {

    @Override
    public EmbeddingModel createEmbeddingModel() {
        var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));
        return new OpenAiEmbeddingModel(
                openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .withModel("text-embedding-3-small")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE
        );

    }
}