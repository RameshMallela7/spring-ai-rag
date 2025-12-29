package com.ai.spring_ai_rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.spring_ai_rag.service.RagService;



@RestController
@RequestMapping("/v1/ai")
public class ControllerClass {

    private final RagService ragService;

    public ControllerClass(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/promptchat")
    public String chat(@RequestBody String message) {
        //return ragService.processChat(message);
        return null;
    }
    
}
