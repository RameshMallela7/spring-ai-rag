package com.ai.spring_ai_rag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

import java.util.logging.Logger;

import org.springframework.ai.bedrock.cohere.BedrockCohereEmbeddingModel;
import org.springframework.ai.bedrock.cohere.api.CohereEmbeddingBedrockApi;
import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Qualifier;


@Configuration
public class ConfigurationsClass {

    private static final Logger logger = Logger.getLogger(ConfigurationsClass.class.getName());


   /* 
    @Bean
    @Primary
    public EmbeddingModel embeddingModel(BedrockRuntimeClient client) {
        return new BedrockCohereEmbeddingModel(new CohereEmbeddingBedrockApi("cohere.embed-english-v3", "us-east-1")
        		
        		
               // client,
                //"amazon.nova-2-multimodal-embeddings-v1:0"
        );
    }*/
    @Bean
    @Primary
    public EmbeddingModel embeddedMode(
        @Qualifier("titanEmbeddingModel") EmbeddingModel titanEmbeddingModel
    ) {
        logger.info("ConfigurationsClass : embeddedMode @bean - Using Titan Embedding Model as Primary Embedding Model.");
        return titanEmbeddingModel;
    }


    

    
    
}
