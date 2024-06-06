package dev.arubino.springai.configuration;

import dev.arubino.springai.factories.imageModelFactory.*;
import org.springframework.ai.image.*;
import org.springframework.context.annotation.*;

@Configuration
public class ImageModelConfig {

    @Bean
    public OpenAiImageModelFactory openAiImageModelFactory() {
        return new OpenAiImageModelFactory();
    }

    public ImageModel get(String model) {
        return switch (model) {
            case Model.OPENAI -> this.openAiImageModelFactory().createImageModel();
            default -> throw new IllegalStateException("Unexpected value: " + model);
        };
    }
}