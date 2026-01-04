package com.ai.spring_ai_rag.service;

import java.util.logging.Logger;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.stereotype.Service;


@Service
public class RagService {
   

    private final Logger logger = Logger.getLogger(RagService.class.getName()); 
    
    private final ChatClient chatClient;


    public RagService(ChatClient.Builder chatClient, VectorStore vectorStore) {
        SearchRequest searchRequest = SearchRequest.builder()
                                            .similarityThreshold(SearchRequest.SIMILARITY_THRESHOLD_ACCEPT_ALL)
                                            .topK(SearchRequest.DEFAULT_TOP_K)
                                            .build();

        this.chatClient = chatClient
                            .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).searchRequest(searchRequest).build())
                            .build();
    }

    public String processChat(String userMessage) {
        logger.info("RagService : Processing user message with RAG capabilities...");
        String response = chatClient.prompt().user(userMessage).call().content();
        return "Processed message: " + response;
    }
        
}
