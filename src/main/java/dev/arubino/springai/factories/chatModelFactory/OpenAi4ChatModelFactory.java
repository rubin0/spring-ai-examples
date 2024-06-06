package dev.arubino.springai.factories.chatModelFactory;

import org.springframework.ai.chat.model.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;

public class OpenAi4ChatModelFactory implements ChatModelFactory {
    @Override
    public ChatModel createChatModel() {
        var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));

        return new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder()
                .withModel(OpenAiApi.ChatModel.GPT_4_O)
                .withTemperature(0.8f) //default
                .build());
    }
}