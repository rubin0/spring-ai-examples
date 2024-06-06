package dev.arubino.springai.service;

import dev.arubino.springai.*;
import org.springframework.ai.converter.*;
import org.springframework.core.convert.support.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OutputConverterService {

    private final ChatClientService chatClientService;

    public OutputConverterService(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    public List<String> chatWithListOutput(String model, PokemonRequest request) {
        var userPromptTemplate = """
                Tell me the names of ten pokémons that are {type} type.
                Consider only the pokémons that are from the {region} region.
                """;

        return chatClientService.getChatClient(model)
                .prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("type", request.type())
                        .param("region", request.region())
                )
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    public Map<String, Object> chatWithMapOutput(String model, PokemonRequest request) {
        var userPromptTemplate = """
                Tell me the pokédex id as key and the relative name of ten pokémons that are {type} type.
                Consider only the pokémons that are from the {region} region.
                """;

        return chatClientService.getChatClient(model)
                .prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("type", request.type())
                        .param("region", request.region())
                )
                .call()
                .entity(new MapOutputConverter());
    }

    public Pokemon chatWithBeanOutput(String model, String pokemon) {
        var userPromptTemplate = """
                Give me information about the pokémon {pokemon}
                """;

        return chatClientService.getChatClient(model)
                .prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("pokemon", pokemon)
                )
                .call()
                .entity(Pokemon.class);
    }

}
