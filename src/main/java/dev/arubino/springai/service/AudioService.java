package dev.arubino.springai.service;

import dev.arubino.springai.configuration.*;
import org.springframework.ai.image.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.*;
import org.springframework.ai.openai.audio.speech.*;
import org.springframework.ai.openai.audio.transcription.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;

import javax.sound.sampled.*;
import java.io.*;

@Service
public class AudioService {

    private final AudioModelConfig audioModelConfig;

    public AudioService(AudioModelConfig audioModelConfig) {
        this.audioModelConfig = audioModelConfig;
    }

    public String transcribe(String model, Resource resource){
        return audioModelConfig.getAudioTranscriptionModel(model)
                .call(new AudioTranscriptionPrompt(resource))
                .getResult()
                .getOutput();
    }

    public String transcribeWithOptions(String model, Resource resource){
        return audioModelConfig.getAudioTranscriptionModel(model)
                .call(new AudioTranscriptionPrompt(resource,
                        OpenAiAudioTranscriptionOptions.builder()
                            .withLanguage("it")
                            .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.VTT)
                                .build()
                ))
                .getResult()
                .getOutput();
    }

    public void speech(String model, String voice, String message) {
        byte[] audioByte = audioModelConfig.getAudioSpeechModel(model)
                .call(new SpeechPrompt(message,
                        OpenAiAudioSpeechOptions.builder()
                                .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.valueOf(voice))
                                .build()
                ))
                .getResult()
                .getOutput();


        try (FileOutputStream fos = new FileOutputStream("output.mp3")) {
            fos.write(audioByte);
            System.out.println("MP3 file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}