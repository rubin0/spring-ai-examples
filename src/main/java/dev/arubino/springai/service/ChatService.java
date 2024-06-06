package dev.arubino.springai.service;

import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.net.*;
import java.util.function.*;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.*;

@Service
public class ChatService {

    private final ChatClientService chatClientService;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessageResource;

    @Value("classpath:/prompts/pokedex.st")
    private Resource pokedexResource;

    public ChatService(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    public String chatWithText(String model, String userMessage) {
        return chatClientService.getChatClient(model)
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String chatWithText(String model, Consumer<ChatClient.UserSpec> userMessage) {
        return chatClientService.getChatClient(model)
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String chatWithText(String model, String userMessage, String systemMessage) {
        return chatClientService.getChatClient(model)
                .prompt()
                .system(systemMessage)
                .user(userMessage)
                .call()
                .content();
    }

    public String chatWithFullResponse(String model, String userMessage) {
        return chatClientService.getChatClient(model)
                .prompt()
                .user(userMessage)
                .call()
                .chatResponse().toString();
    }

    public String externalSystemChatWithText(String model, String userMessage) {
        return chatClientService.getChatClient(model)
                .prompt()
                .system(systemMessageResource)
                .user(userMessage)
                .call()
                .content();
    }

    public String chatWithTemplate(String model, String pokemon) {
        return chatClientService.getChatClient(model)
                .prompt()
                .system(pokedexResource)
                .user(userSpec -> userSpec
                        .text("""
                              Tell me about {pokemon}
                              """)
                        .param("pokemon", pokemon))
                .call()
                .content();
    }

    public String chatWithMemory(String model, String userMessage, String id) {
        return chatClientService.getChatClientWithMemory(model)
                .prompt()
                .user(userMessage)
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, id)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .call()
                .content();
    }

    public String chatWithImageUrlMediaPrompt(String model, String imageUrl) throws MalformedURLException {
        UrlResource resource = new UrlResource(imageUrl);
        return chatClientService.getChatClient(model, true)
                .prompt()
                .user(userSpec -> userSpec
                        .text("Analyse the image and try to explain to me why is funny in concise sentence.")
                        .media(new Media(MimeTypeUtils.IMAGE_PNG, resource))
                )
                .call()
                .content();
    }
}