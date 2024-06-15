# Spring AI Reference Project

This Spring AI project was used as a reference for a tech lunch held at Welld Sagl during July 2024.

It uses IntellijJ IDEA HTTP client in order to show the project's feature.

You can find the slides [here](https://shorturl.at/qVGyS)
## Overview

The project showcases various features of a Spring AI application, including:

- **[Chatbot](src%2Fmain%2Fhttp%2F01-ChatWithLLMs.http)**
  - [System messages](src%2Fmain%2Fhttp%2F02-SystemMessages.http)
  - [Prompts templating](src%2Fmain%2Fhttp%2F03-PromptsTemplating.http)
  - [Persistent chat history](src%2Fmain%2Fhttp%2F03-PromptsTemplating.http)
  - [Structured output to POJOs](src%2Fmain%2Fhttp%2F04-StructuredOutput.http)
- **Classification**
  - [Embeddings](src%2Fmain%2Fhttp%2F05-Embeddings.http)
  - [Semantic search](src%2Fmain%2Fhttp%2F07-SemanticSearch.http)
  - [Classification](src%2Fmain%2Fhttp%2F06-Classification.http)
- **Bring your own data**
    - [Prompt stuffing](src%2Fmain%2Fhttp%2F08-PromptStuffing.http)
    - [Retrieval Augmented Generation (RAG)](src%2Fmain%2Fhttp%2F09-Rag.http)
    - [Structured data extraction](src%2Fmain%2Fhttp%2F10-StructuredDataExtraction.http)
    - [Functions calling](src%2Fmain%2Fhttp%2F11-FunctionCalling.http)
- **Multimodality**
  - [Image recognition](src%2Fmain%2Fhttp%2F12-ImageRecognition.http)
  - [Image generation](src%2Fmain%2Fhttp%2F13-ImageGeneration.http)
  - [Audio transcription](src%2Fmain%2Fhttp%2F14-AudioModel.http)
  - [Text to speech](src%2Fmain%2Fhttp%2F14-AudioModel.http)
- **[Logging](src%2Fmain%2Fjava%2Fdev%2Farubino%2Fspringai%2FLoggingAdvisor.java)**
- **[Evaluation testing](src%2Ftest%2Fjava%2Fdev%2Farubino%2Fspringai%2FSpringaiApplicationTests.java)**
- **[Retry policies](src%2Fmain%2Fresources%2Fapplication.properties)**

## Prerequisites

To run the application locally:

1. Java 21
2. [Ollama](https://ollama.com/) installed locally with [llama3](https://ollama.com/library/llama3) and [llava](https://ollama.com/library/llava) models
3. [OpenAI apikey](https://platform.openai.com/api-keys) stored in an environment variable `OPENAI_API_KEY`
4. [Mistral apikey](https://console.mistral.ai/api-keys/) stored in an environment variable `MISTRAL_AI_API_KEY`
5. Run the application:
    ```
    mvn spring-boot:run
    ```

## License

This project is licensed under the MIT License. See the LICENSE file for details.
