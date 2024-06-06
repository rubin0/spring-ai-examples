package dev.arubino.springai.service;

import dev.arubino.springai.configuration.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.image.*;
import org.springframework.ai.openai.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.net.*;
import java.util.function.*;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.*;

@Service
public class ImageGenerationService {

    private final ImageModelConfig imageModelConfig;

    public ImageGenerationService(ImageModelConfig imageModelConfig) {
        this.imageModelConfig = imageModelConfig;
    }

    public String generate(String model, String userMessage) {
        return imageModelConfig.get(model)
                .call(new ImagePrompt(userMessage))
                .getResult()
                .getOutput()
                .getUrl();
    }

    public String generateWithOpenAiOption(String model, String userMessage) {
        return imageModelConfig.get(model)
                .call(new ImagePrompt(userMessage, OpenAiImageOptions.builder()
                        .withQuality("standard")
                        .withN(2)
                        .withHeight(1024)
                        .withWidth(1024)
                        .build()))
                .getResult()
                .getOutput()
                .getUrl();
    }
}