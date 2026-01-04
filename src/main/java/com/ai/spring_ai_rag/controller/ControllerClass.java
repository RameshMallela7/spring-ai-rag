package com.ai.spring_ai_rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.spring_ai_rag.service.NormalService;
import com.ai.spring_ai_rag.service.RagService;



@RestController
@RequestMapping("/v1/ai")
public class ControllerClass {

    private final RagService ragService;

    private final NormalService normalService;

    public ControllerClass(RagService ragService, NormalService normalService) {
        this.ragService = ragService;
        this.normalService = normalService;
    }

    @PostMapping("/ragpromptchat")
    public String ragChat(@RequestBody String message) {
        return ragService.processChat(message);
        //return null;
    }
    
    @PostMapping("/promptchat")
    public String chat(@RequestBody String message) {
        return normalService.processChat(message);
        //return null;
    }
    
}
