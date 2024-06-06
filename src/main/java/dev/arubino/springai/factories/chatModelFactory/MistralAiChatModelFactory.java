package dev.arubino.springai.factories.chatModelFactory;

import org.springframework.ai.chat.model.*;
import org.springframework.ai.mistralai.*;
import org.springframework.ai.mistralai.api.*;

public class MistralAiChatModelFactory implements ChatModelFactory {

    @Override
    public ChatModel createChatModel() {
        var mistralAiApi = new MistralAiApi(System.getenv("MISTRAL_AI_API_KEY"));

        return new MistralAiChatModel(mistralAiApi,
                MistralAiChatOptions.builder()
                        .withModel(MistralAiApi.ChatModel.LARGE.getValue())
                        .withTemperature(0.8f) //default
                        .build());
    }
}