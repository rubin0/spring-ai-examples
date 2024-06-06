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
    public String recognition(@PathVariable(name = "model") String model,
                              @RequestBody String userMessage) throws MalformedURLException {
        return imageGenerationService.generate(model, userMessage);
    }

}
