package dev.arubino.springai.factories.audioModelFactory;

import org.springframework.ai.openai.*;

public interface AudioModelFactory {
    OpenAiAudioTranscriptionModel createTranscriptionModel();
    OpenAiAudioSpeechModel createSpeechModel();
}