package dev.arubino.springai.factories.chatModelFactory;

import org.springframework.ai.chat.model.*;
import org.springframework.ai.ollama.*;
import org.springframework.ai.ollama.api.*;

public class OllamaMultiModalChatModelFactory implements ChatModelFactory {

    @Override
    public ChatModel createChatModel() {
        var ollamaApi = new OllamaApi();

        return new OllamaChatModel(ollamaApi,
                OllamaOptions.create()
                        .withModel("llava")
                        .withTemperature(0.8f));
    }
}