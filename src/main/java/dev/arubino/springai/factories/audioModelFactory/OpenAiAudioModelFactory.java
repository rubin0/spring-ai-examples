package dev.arubino.springai.factories.audioModelFactory;

import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;

public class OpenAiAudioModelFactory implements AudioModelFactory {
    @Override
    public OpenAiAudioTranscriptionModel createTranscriptionModel() {
        var openAiAudioApi = new OpenAiAudioApi((System.getenv("OPENAI_API_KEY")));

        return new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

    @Override
    public OpenAiAudioSpeechModel createSpeechModel() {
        var openAiAudioApi = new OpenAiAudioApi((System.getenv("OPENAI_API_KEY")));

        return new OpenAiAudioSpeechModel(openAiAudioApi);
    }
}
