package dev.arubino.springai.factories.imageModelFactory;

import org.springframework.ai.image.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;

public class OpenAiImageModelFactory implements ImageModelFactory {

    @Override
    public ImageModel createImageModel() {
        var openAiImageApi = new OpenAiImageApi(System.getenv("OPENAI_API_KEY"));

        return new OpenAiImageModel(openAiImageApi);
    }
}