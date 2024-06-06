package dev.arubino.springai.factories.chatModelFactory;

import org.springframework.ai.chat.model.*;

public interface ChatModelFactory {
    ChatModel createChatModel();
}