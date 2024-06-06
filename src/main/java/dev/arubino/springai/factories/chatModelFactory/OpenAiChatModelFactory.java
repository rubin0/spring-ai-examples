package dev.arubino.springai.factories.chatModelFactory;

import org.springframework.ai.chat.model.*;
import org.springframework.ai.model.function.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;
import org.springframework.retry.support.*;

public class OpenAiChatModelFactory implements ChatModelFactory {

    private final FunctionCallbackContext functionCallbackContext;

    public OpenAiChatModelFactory(FunctionCallbackContext functionCallbackContext) {
        this.functionCallbackContext = functionCallbackContext;
    }

    @Override
    public ChatModel createChatModel() {
        var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));

        return new OpenAiChatModel(openAiApi,
                OpenAiChatOptions.builder()
                    .withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO)
                    .withTemperature(0.8f) //default
                    .build(),
                functionCallbackContext,
                RetryTemplate.defaultInstance()
        );
    }
}