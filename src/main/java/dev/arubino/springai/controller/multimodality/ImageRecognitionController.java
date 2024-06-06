package dev.arubino.springai.controller.multimodality;

import dev.arubino.springai.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("image/{model}")
public class ImageRecognitionController {

    private final ChatService chatService;

    public ImageRecognitionController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/recognition", produces = MediaType.TEXT_PLAIN_VALUE)
    public String recognition(@PathVariable(name = "model") String model,
                              @RequestBody String imageUrl) throws MalformedURLException {
        return chatService.chatWithImageUrlMediaPrompt(model, imageUrl);
    }

}
