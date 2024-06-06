package dev.arubino.springai.controller.promptChat;

import dev.arubino.springai.configuration.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.model.function.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat/simple/")
public class SimpleChatController {

    private final ChatModelConfig chatModelConfig;
    private final FunctionCallbackContext functionCallbackContext;

    public SimpleChatController(ChatModelConfig chatModelConfig,
                                FunctionCallbackContext functionCallbackContext) {
        this.chatModelConfig = chatModelConfig;
        this.functionCallbackContext = functionCallbackContext;
    }


    @GetMapping("ollama")
    public String simpleOllamaChat() {
        var ollamaChatModel = chatModelConfig.ollamaChatModelFactory().createChatModel();

        return ChatClient.create(ollamaChatModel)
                .prompt()
                .user("""
                        Hello! How is it going today?
                        """)
                .call()
                .content();
    }

    @GetMapping("openai")
    public String simpleOpenAiChat() {
        var ollamaChatModel = chatModelConfig.openAiChatModelFactory(functionCallbackContext).createChatModel();

        return ChatClient.create(ollamaChatModel)
                .prompt()
                .user("""
                        Hello! How is it going today?
                        """)
                .call()
                .content();
    }

    @GetMapping("mistral")
    public String simpleMistralChat() {
        var ollamaChatModel = chatModelConfig.mistralAiChatModelFactory().createChatModel();

        return ChatClient.create(ollamaChatModel)
                .prompt()
                .user("""
                        Hello! How is it going today?
                        """)
                .call()
                .content();
    }
}
