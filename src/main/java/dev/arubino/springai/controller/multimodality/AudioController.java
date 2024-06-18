package dev.arubino.springai.controller.multimodality;

import dev.arubino.springai.service.*;
import org.springframework.ai.openai.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.*;
import java.io.*;

@RestController
@RequestMapping("audio/{model}")
public class AudioController {

    private final AudioService audioService;

    @Value("classpath:/audio/audio.mp3")
    private Resource audio1;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping(value = "/transcription", produces = MediaType.TEXT_PLAIN_VALUE)
    public String transcription(@PathVariable(name = "model") String model) {
        return audioService.transcribe(model, audio1);
    }

    @PostMapping(value = "/tts/{voice}")
    public String tts(@PathVariable(name = "model") String model,
                      @PathVariable(name = "voice") String voice,
                    @RequestBody String message) {
        audioService.speech(model, voice, message);

        return "OK";
    }

}
