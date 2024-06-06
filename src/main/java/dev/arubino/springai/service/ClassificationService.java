package dev.arubino.springai.service;

import org.springframework.stereotype.*;

@Service
public class ClassificationService {

    private final ChatClientService chatClientService;


    public ClassificationService(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    public <T> T classify(String model, String userMessage, String examples, Class<T> clazz) {
        return chatClientService.getChatClient(model)
                .prompt()
                .user(userSpec -> userSpec.text("""
                    Classify the type of the provided text and return the classification type.

                    EXAMPLES:
                    {examples}
                    ---------------------
                    TEXT:
                    {text}
                    ---------------------
                    """)
                        .param("text", userMessage)
                        .param("examples", examples)
                )
                .call()
                .entity(clazz);
    }
}