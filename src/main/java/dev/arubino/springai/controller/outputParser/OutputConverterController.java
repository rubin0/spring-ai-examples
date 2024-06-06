package dev.arubino.springai.controller.outputParser;

import dev.arubino.springai.*;
import dev.arubino.springai.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("chat/structured/{model}")
public class OutputConverterController {

    private final OutputConverterService outputConverterService;

    public OutputConverterController(OutputConverterService outputConverterService) {
        this.outputConverterService = outputConverterService;
    }

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<String> chatWithListOutput(@PathVariable(name = "model") String model,
                                           @RequestBody PokemonRequest pokemonRequest) {
        return outputConverterService.chatWithListOutput(model, pokemonRequest);
    }

    @PostMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> chatWithMapOutput(@PathVariable(name = "model") String model,
                                                 @RequestBody PokemonRequest pokemonRequest) {
        return outputConverterService.chatWithMapOutput(model, pokemonRequest);
    }

    @PostMapping(value = "/bean", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon chatWithBeanOutput(@PathVariable(name = "model") String model,
                                      @RequestBody String pokemon) {
        return outputConverterService.chatWithBeanOutput(model, pokemon);
    }


}
