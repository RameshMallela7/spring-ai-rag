package com.ai.spring_ai_rag.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/ai")
public class ControllerClass {

    private final GoogleGenAiChatModel googleGenAiChatModel;
    private final VectorStore vectorStore;

    public ControllerClass(GoogleGenAiChatModel googleGenAiChatModel,  VectorStore vectorStore) {
        this.googleGenAiChatModel = googleGenAiChatModel;
        this.vectorStore = vectorStore;
    }

    @PostMapping("/prompt-chat")
    public String chat(@RequestBody String message) {
        //add logic to call gemini AI with prompt message
    	 //SearchRequest searchRequest = SearchRequest.builder().similarityThreshold(0.75).topK(4).build();
    	 SearchRequest searchRequest = SearchRequest.builder()
                                            .similarityThreshold(SearchRequest.SIMILARITY_THRESHOLD_ACCEPT_ALL)
                                            .topK(SearchRequest.DEFAULT_TOP_K)
                                            .build();
    	 
    	 //
    String respo = ChatClient.builder(googleGenAiChatModel)
        .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).searchRequest(searchRequest).build())
        .build()
        .prompt()
        .user(message)
        .call()
        .content();
    return respo;
    }
    
}
