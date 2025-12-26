
package com.ai.spring_ai_rag.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/*
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
*/ 

//  --1--

@Service
public class DocumentInjectionService// implements CommandLineRunner 
{
    /*

    @Autowired
    private JdbcClient jdbcClient;

    @Autowired
    private VectorStore vectorStore;


    @Override
    public void run(String... args) throws Exception {
        // Initialization logic here
        Integer count = jdbcClient.sql("SELECT count(*) FROM vector_store").query(Integer.class).single();
        if (count == 0) {
            TikaDocumentReader tikaDocumentReader = new TikaDocumentReader("path/to/your/documents");
            //List<Document> documents = tikaDocumentReader.readDocumentsFromDirectory("path/to/your/documents");

            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> textChunks = textSplitter.split(tikaDocumentReader.read());

            vectorStore.accept(textChunks);
        }
    }
    
     */
}
