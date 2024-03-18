package com.balarawool.springai;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OllamaController {

    @GetMapping("/chat")
    public String chat(@RequestParam Map<String, String> params) {
        var ollamaApi = new OllamaApi();

        var chatClient = new OllamaChatClient(ollamaApi)
                //.withModel(MODEL) // You could set model here already.
                .withDefaultOptions(OllamaOptions.create()
                        .withModel("llama2")
                        //.withModel(OllamaOptions.DEFAULT_MODEL) //Default model is mistral, but we are running llama2.
                        .withTemperature(0.9f));

        ChatResponse response = chatClient.call(
                new Prompt(params.get("prompt")));

        return response.toString();
    }

    @GetMapping("/embedding")
    public String embedding(@RequestParam Map<String, String> params) {
        var ollamaApi = new OllamaApi();

        var embeddingClient = new OllamaEmbeddingClient(ollamaApi)
                .withDefaultOptions(OllamaOptions.create()
                        .withModel("llama2"));

        EmbeddingResponse embeddingResponse = embeddingClient
                .embedForResponse(List.of(params.get("data")));


        return embeddingResponse.getResults().get(0).getOutput().toString();
    }
}
