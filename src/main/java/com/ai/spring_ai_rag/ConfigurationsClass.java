package com.ai.spring_ai_rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.bedrock.api.*;
import org.springframework.ai.bedrock.chat.BedrockChatModel;


@Configuration
public class ConfigurationsClass {

    public BedrockChatModule bedrockChatModule() {
        return new BedrockChatModule();
    }

    @Bean
    public ChatClient chatClient() {
        // Build a ChatClient without calling undefined 'prompt' method.
        return ChatClient.builder(bedrockChatModule())
                // Configure your ChatClient here (e.g., set API keys, endpoints, etc.)
                .build();
    }

}
