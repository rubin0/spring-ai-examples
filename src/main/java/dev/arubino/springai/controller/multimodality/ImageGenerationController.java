package dev.arubino.springai.controller.multimodality;

import dev.arubino.springai.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("chat/prompt/{model}")
public class ImageGenerationController {

    private final ImageGenerationService imageGenerationService;

    public ImageGenerationController(ImageGenerationService imageGenerationService) {
        this.imageGenerationService = imageGenerationService;
    }

    @PostMapping(value = "/image")
    public String generate(@PathVariable(name = "model") String model,
                              @RequestParam(defaultValue = "1") int n,
                              @RequestParam(defaultValue = "512") int height,
                              @RequestParam(defaultValue = "512") int width,
                              @RequestBody String userMessage) {
        return imageGenerationService.generateWithOpenAiOption(model, userMessage, n, height, width);
    }


}
