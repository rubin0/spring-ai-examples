package dev.arubino.springai.service;

import dev.arubino.springai.configuration.*;
import org.springframework.ai.document.*;
import org.springframework.ai.transformer.splitter.*;
import org.springframework.ai.vectorstore.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

@Service
public class VectorStoreService {

    private final VectorStoreConfig vectorStoreConfig;

    @Value("vectorstore.json")
    private String vectorStorePath;

    public VectorStoreService(VectorStoreConfig vectorStoreConfig) {
        this.vectorStoreConfig = vectorStoreConfig;
    }

    public void loadDocument(String embeddingModel, List<Document> documents) {
        SimpleVectorStore vectorStore = vectorStoreConfig.getVectorStore(embeddingModel);

        File vectorStoreFile = new File(vectorStorePath);
        vectorStore.add(new TokenTextSplitter().apply(documents));
        vectorStore.save(vectorStoreFile);
    }

    public List<String> similaritySearch(String embeddingModel, String query) {
        var vectorStore = vectorStoreConfig.getVectorStore(embeddingModel);

        SearchRequest searchRequest = SearchRequest.query(query).withTopK(2);

        return vectorStore
                .similaritySearch(searchRequest)
                .stream().map(Document::getContent).collect(Collectors.toList());
    }

    public List<String> similaritySearch(String embeddingModel, String query, int topK) {
        var vectorStore = vectorStoreConfig.getVectorStore(embeddingModel);

        SearchRequest searchRequest = SearchRequest.query(query)
                .withTopK(topK);

        return vectorStore
                .similaritySearch(searchRequest)
                .stream().map(Document::getContent).collect(Collectors.toList());
    }
}
