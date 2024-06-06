package dev.arubino.springai.controller.byod;

import dev.arubino.springai.service.*;
import org.apache.groovy.util.*;
import org.springframework.ai.document.*;
import org.springframework.ai.reader.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("chat/rag/{model}")
public class RagController {

    private final VectorStoreService vectorStoreService;
    private final ChatClientService chatClientService;

    @Value("classpath:/documents/story1.md")
    private Resource document1;

    @Value("classpath:/documents/story2.txt")
    private Resource document2;

    public RagController(VectorStoreService vectorStoreService,
                         ChatClientService chatClientService) {
        this.vectorStoreService = vectorStoreService;
        this.chatClientService = chatClientService;
    }

    @PostMapping(value = "/load")
    public String load(@PathVariable(name = "model") String model,
                       @RequestParam(defaultValue = "false") boolean enhance) {
        List<Document> documents = new ArrayList<>();

        documents.addAll(createDocumentsFromResource(model, document1, Maps.of("location", "North Pole"), enhance));
        documents.addAll(createDocumentsFromResource(model, document2, Maps.of("location", "Italian ALps"), enhance));

        vectorStoreService.loadDocument(model, documents);
        return "Documents loaded!";
    }

    private List<Document> createDocumentsFromResource(String model, Resource doc, Map<String, String> metadata,
                                                       boolean enhance) {

        var textReader = new TextReader(doc);
        textReader.getCustomMetadata().putAll(metadata);
        textReader.setCharset(Charset.defaultCharset());

        List<Document> documents = new ArrayList<>(textReader.get());

        if (enhance) {
            documents = chatClientService.getKeywordMetadataEnricher(model).apply(documents);
            documents = chatClientService.getSummaryMetadataEnricher(model).apply(documents);
        }

        documents.addAll(textReader.get());

        return documents;
    }

    @PostMapping(value = "/doc",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String chatWithDocument(@PathVariable(name = "model") String model,
                                   @RequestBody String userMessage) {
        var systemPromptTemplate = """
                You are a helpful assistant, conversing with a user about the subjects contained in a set of documents.
                Use the information from the DOCUMENTS section to provide accurate answers. If unsure or if the answer
                isn't found in the DOCUMENTS section, simply state that you don't know the answer and do not mention
                the DOCUMENTS section.
                
                DOCUMENTS:
                {documents}
                """;

        List<String> similarDocuments = vectorStoreService.similaritySearch(model, userMessage);
        String content = similarDocuments.stream().collect(Collectors.joining(System.lineSeparator()));

        return chatClientService
                .getChatClientWithDocument(model)
                .prompt()
                .system(systemSpec -> systemSpec
                        .text(systemPromptTemplate)
                        .param("documents", content)
                )
                .user(userMessage)
                .call()
                .content();

    }

    @PostMapping(value = "/similaritySearch",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> similaritySearch(@PathVariable(name = "model") String model,
                                         @RequestBody String userMessage) {
        return vectorStoreService.similaritySearch(model, userMessage);
    }

}
