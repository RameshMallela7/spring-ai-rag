package com.ai.spring_ai_rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class NormalService {

     private final ChatClient chatClient;


    public NormalService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String processChat(String userMessage) {
        String response = chatClient.prompt().user(userMessage).call().content();
        return "Processed message: " + response;
    }
}
