package com.ai.spring_ai_rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.spring_ai_rag.service.NormalPromptService;



@RestController
@RequestMapping("/v1/ai")
public class ControllerClass {

    private final NormalPromptService normalPromptService;

    public ControllerClass(NormalPromptService normalPromptService) {
        this.normalPromptService = normalPromptService;
    }

    @PostMapping("/promptchat")
    public String chat(@RequestBody String message) {
        return normalPromptService.normalProcessChat(message);
    }
    
}
