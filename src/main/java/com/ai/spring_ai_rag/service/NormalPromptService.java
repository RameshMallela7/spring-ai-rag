package com.ai.spring_ai_rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class NormalPromptService {

    private ChatClient chatClient;

    

    public String normalProcessChat(String userMessage) {
        String response = chatClient.prompt().user(userMessage).call().content();
        return "Processed message: " + response;
    }
}
