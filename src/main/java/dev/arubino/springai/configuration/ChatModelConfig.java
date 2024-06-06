package dev.arubino.springai.configuration;

import dev.arubino.springai.factories.chatModelFactory.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.chat.model.*;
import org.springframework.ai.model.function.*;
import org.springframework.context.annotation.*;

@Configuration
public class ChatModelConfig {

    @Bean
    public OllamaChatModelFactory ollamaChatModelFactory() {
        return new OllamaChatModelFactory();
    }

    @Bean
    public OllamaMultiModalChatModelFactory ollamaMultiModalChatModelFactory() {
        return new OllamaMultiModalChatModelFactory();
    }

    @Bean
    public OpenAiChatModelFactory openAiChatModelFactory(FunctionCallbackContext functionCallbackContext) {
        return new OpenAiChatModelFactory(functionCallbackContext);
    }

    @Bean
    public OpenAi4ChatModelFactory openAi4ChatModelFactory() {
        return new OpenAi4ChatModelFactory();
    }

    @Bean
    public MistralAiChatModelFactory mistralAiChatModelFactory() {
        return new MistralAiChatModelFactory();
    }

    @Bean
    public InMemoryChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public FunctionCallbackContext functionCallbackContext() {
        return new FunctionCallbackContext();
    }

    public ChatModel get(String model) {
        return get(model, false);
    }

    public ChatModel get(String model, boolean multimodal) {
        return switch (model) {
            case Model.OLLAMA -> {
                if (multimodal) {
                    yield this.ollamaMultiModalChatModelFactory().createChatModel();
                } else {
                    yield this.ollamaChatModelFactory().createChatModel();
                }
            }
            case Model.OPENAI -> this.openAiChatModelFactory(functionCallbackContext()).createChatModel();
            case Model.OPENAI4 -> this.openAi4ChatModelFactory().createChatModel();
            case Model.MISTRAL -> this.mistralAiChatModelFactory().createChatModel();
            default -> throw new IllegalStateException("Unexpected value: " + model);
        };
    }
}