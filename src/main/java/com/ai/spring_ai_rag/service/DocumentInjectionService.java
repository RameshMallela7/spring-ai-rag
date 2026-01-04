
package com.ai.spring_ai_rag.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;


@Service
public class DocumentInjectionService implements CommandLineRunner {

	Logger logger = Logger.getLogger(DocumentInjectionService.class.getName());

	@Autowired
	private JdbcClient jdbcClient;

	@Autowired
	private VectorStore vectorStore;

	@Value("classpath:/pdf/Unique_Dataset-1.pdf")
	private Resource path;

	@Override
	public void run(String... args) throws Exception {
		// Initialization logic here
		logger.info("Document	InjectionService : Checking and injecting documents into Vector Store if needed...");
		Integer count = jdbcClient.sql("SELECT count(*) FROM vector_store").query(Integer.class).single();
		logger.info("DocumentInjectionService : Current document count in Vector Store: " + count);
		if (count < 10) {
			logger.info("DocumentInjectionService : No documents found in Vector Store. Injecting documents...");
			TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(path);

			List<Document> rawDocs = tikaDocumentReader.read();

			TextSplitter textSplitter = new TokenTextSplitter();
			List<Document> textChunks = textSplitter.split(rawDocs);
			logger.info("DocumentInjectionService : Number of document chunks to be injected: " + textChunks.size());

			vectorStore.add(textChunks);
			logger.info("DocumentInjectionService : Document injection completed.");
		}

	}

}
