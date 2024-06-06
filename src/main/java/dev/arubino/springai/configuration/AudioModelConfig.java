package dev.arubino.springai.configuration;

import dev.arubino.springai.factories.audioModelFactory.*;
import org.springframework.ai.openai.*;
import org.springframework.context.annotation.*;

@Configuration
public class AudioModelConfig {

    @Bean
    public OpenAiAudioModelFactory openAiAudioModelFactory() {
        return new OpenAiAudioModelFactory();
    }

    public OpenAiAudioTranscriptionModel getAudioTranscriptionModel(String model) {
        return switch (model) {
            case Model.OPENAI -> this.openAiAudioModelFactory().createTranscriptionModel();
            default -> throw new IllegalStateException("Unexpected value: " + model);
        };
    }

    public OpenAiAudioSpeechModel getAudioSpeechModel(String model) {
        return switch (model) {
            case Model.OPENAI -> this.openAiAudioModelFactory().createSpeechModel();
            default -> throw new IllegalStateException("Unexpected value: " + model);
        };
    }
}