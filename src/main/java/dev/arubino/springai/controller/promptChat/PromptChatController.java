package dev.arubino.springai.controller.promptChat;

import dev.arubino.springai.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "chat/prompt/{model}", produces = MediaType.TEXT_PLAIN_VALUE)
public class PromptChatController {

    private final ChatService chatService;

    public PromptChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/")
    public String promptChat(@PathVariable(name = "model") String model,
                             @RequestBody String userMessage) {
        return chatService.chatWithText(model, userMessage);
    }


    @PostMapping(value = "/full", produces = MediaType.APPLICATION_JSON_VALUE)
    public String fullPromptChat(@PathVariable(name = "model") String model,
                                 @RequestBody String userMessage) {
        return chatService.chatWithFullResponse(model, userMessage);
    }

    @PostMapping(value = "/system")
    public String systemPromptChat(@PathVariable(name = "model") String model,
                                   @RequestBody String userMessage) {
        var systemMessage = """
                You are the best Pokémon trainer in the world of Kanto!
                Your name is Ash Ketchum.
                You are a 10 years old kid.
                You can only answer questions that are relevant within the Pokémon world.
                Reject any other argument!
                """;
        return chatService.chatWithText(model, userMessage, systemMessage);
    }

    @PostMapping(value = "/external")
    public String externalSystemPromptChat(@PathVariable(name = "model") String model,
                                           @RequestBody String userMessage) {
        return chatService.externalSystemChatWithText(model, userMessage);
    }

    @PostMapping(value = "/pokedex")
    public String chatWithTemplate(@PathVariable(name = "model") String model,
                                   @RequestParam(value = "pokemon", defaultValue = "pikachu") String pokemon) {
        return chatService.chatWithTemplate(model, pokemon);
    }

    @PostMapping(value = "/assistant/{chatId}")
    public String chatWithMemory(@PathVariable(name = "model") String model,
                                 @PathVariable(name = "chatId") String chatId,
                                 @RequestBody String userMessage) {
        return chatService.chatWithMemory(model, userMessage, chatId);
    }

}
