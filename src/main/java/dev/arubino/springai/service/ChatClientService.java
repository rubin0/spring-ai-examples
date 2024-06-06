package dev.arubino.springai.service;

import dev.arubino.springai.*;
import dev.arubino.springai.configuration.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.chat.prompt.*;
import org.springframework.ai.transformer.*;
import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ChatClientService implements ApplicationContextAware {

    private final ChatModelConfig chatModelConfig;
    private final ChatMemory chatMemory;
    private static ApplicationContext applicationContext;

    public ChatClientService(ChatModelConfig chatModelConfig,
                             ChatMemory chatMemory) {
        this.chatModelConfig = chatModelConfig;
        this.chatMemory = chatMemory;
    }

    public ChatClient getChatClient(String model, boolean multimodal) {
        return ChatClient.builder(chatModelConfig.get(model, multimodal))
                .defaultAdvisors(
                        new LoggingAdvisor()
                )
                .build();
    }

    public ChatClient getChatClient(String model) {
        return ChatClient.builder(chatModelConfig.get(model))
                .defaultAdvisors(
                        new LoggingAdvisor()
                )
                .build();
    }


    public ChatClient getChatClientWithMemory(String model) {
        return ChatClient.builder(chatModelConfig.get(model))
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new LoggingAdvisor()
                )
                .build();
    }

    public ChatClient getChatClientWithDocument(String model) {
        return ChatClient.builder(chatModelConfig.get(model))
                .defaultOptions(
                        ChatOptionsBuilder
                        .builder()
                        .withTemperature(0.7f)
                                .build()
                )
                .defaultAdvisors(
                        new LoggingAdvisor()
                )
                .build();
    }

    public SummaryMetadataEnricher getSummaryMetadataEnricher(String model) {
        return new SummaryMetadataEnricher(chatModelConfig.get(model), List.of(
                SummaryMetadataEnricher.SummaryType.PREVIOUS,
                SummaryMetadataEnricher.SummaryType.CURRENT,
                SummaryMetadataEnricher.SummaryType.NEXT));
    }

    public KeywordMetadataEnricher getKeywordMetadataEnricher(String model) {
        return new KeywordMetadataEnricher(chatModelConfig.get(model), 3);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ChatClientService.applicationContext = applicationContext;
    }
}
