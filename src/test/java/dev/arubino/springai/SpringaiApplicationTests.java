package dev.arubino.springai;

import dev.arubino.springai.configuration.*;
import dev.arubino.springai.configuration.Model;
import dev.arubino.springai.controller.byod.*;
import dev.arubino.springai.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.model.*;
import org.springframework.ai.evaluation.*;
import org.springframework.ai.model.*;
import org.springframework.ai.vectorstore.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringaiApplicationTests {

	@Autowired
	private ChatClientService chatClientService;

	@Autowired
	private ChatModelConfig chatModelConfig;

	@Autowired
	private RagController ragController;

	@Autowired
	private VectorStoreConfig vectorStoreConfig;

	@Test
	void testEvaluation() {
		ragController.load(Model.OLLAMA, false);
		ragController.load(Model.OPENAI, false);

		var userPromptTemplate = """
                What is the story of Elara?
                """;

		ChatResponse response = chatClientService.getChatClient(Model.OLLAMA)
				.prompt()
				.advisors(new QuestionAnswerAdvisor(vectorStoreConfig.getVectorStore(Model.OLLAMA), SearchRequest.defaults()))
				.user(userSpec -> userSpec
						.text(userPromptTemplate)
				)
				.call()
				.chatResponse();

		var relevancyEvaluator = new RelevancyEvaluator(ChatClient.builder(chatModelConfig.get(Model.OPENAI)));

		EvaluationRequest evaluationRequest = new EvaluationRequest(userPromptTemplate,
				(List<Content>) response.getMetadata().get(QuestionAnswerAdvisor.RETRIEVED_DOCUMENTS),
				response);

		EvaluationResponse evaluationResponse = relevancyEvaluator.evaluate(evaluationRequest);

		assertTrue(evaluationResponse.isPass(), "Response is not relevant to the question");

	}

}
