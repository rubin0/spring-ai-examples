package dev.arubino.springai.configuration;

import org.springframework.ai.model.function.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;

@Configuration
public class FunctionCallbackContextConfiguration {

    private final ApplicationContext applicationContext;

    public FunctionCallbackContextConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public FunctionCallbackContext functionCallbackContext(){
        var functionCallContext =  new FunctionCallbackContext();
        functionCallContext.setApplicationContext(applicationContext);
        return functionCallContext;
    }
}
