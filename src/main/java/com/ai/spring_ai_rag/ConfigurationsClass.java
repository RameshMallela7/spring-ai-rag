package com.ai.spring_ai_rag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Qualifier;


@Configuration
public class ConfigurationsClass {

    private static final Logger logger = Logger.getLogger(ConfigurationsClass.class.getName());


    ConfigurationsClass() {
        logger.info("ConfigurationsClass : Configuration class initialized.");
    }
   /* 
    @Bean
    @Primary
    public EmbeddingModel embeddingModel(BedrockRuntimeClient client) {
        return new BedrockCohereEmbeddingModel(new CohereEmbeddingBedrockApi("cohere.embed-english-v3", "us-east-1")
        		
        		
               // client,
                //"amazon.nova-2-multimodal-embeddings-v1:0"
        );
    }
    @Bean
    @Primary
    public EmbeddingModel embeddedMode(
        @Qualifier("titanEmbeddingModel") EmbeddingModel titanEmbeddingModel
    ) {
        logger.info("ConfigurationsClass : embeddedMode @bean - Using Titan Embedding Model as Primary Embedding Model.");
        return titanEmbeddingModel;
    }

    */


    

    
    
}
