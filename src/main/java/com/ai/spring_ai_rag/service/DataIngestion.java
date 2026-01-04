package com.ai.spring_ai_rag.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class DataIngestion implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(DataIngestion.class.getName());

	private final VectorStore vectorStore;
	
	private JdbcClient jdbcClient;

	public DataIngestion(VectorStore vectorStore, JdbcClient jdbcClient) {
		this.vectorStore = vectorStore;
		this.jdbcClient = jdbcClient;
	}

	@Override
	public void run(String... args) throws Exception {
        logger.info("DataIngestion : Loading sample documents into VectorStore...");
		Integer count = jdbcClient.sql("SELECT count(*) FROM vector_store").query(Integer.class).single();
		logger.info("DataIngestion : Current document count in Vector Store: " + count);
		if (count < 10) {
		List<Document> documents = List.of(new Document(
				"StarlightDB is a serverless graph database designed for real-time analytics on complex, interconnected data."),
				new Document(
						"The core of StarlightDB is its 'Quantum-Leap' query engine, which uses speculative execution to deliver query results up to 100x faster than traditional graph databases."),
				new Document(
						"StarlightDB features 'Chrono-Sync' for effortless time-travel queries, allowing developers to query the state of their graph at any point in the past."),
				new Document(
						"StarlightDB includes a built-in visualization tool called 'Nebula' that renders interactive 3D graphs directly within the development environment for easier analysis."),
				new Document(
						"Security in StarlightDB is handled by 'Cosmic Shield', which provides end-to-end encryption and fine-grained access control at the node and edge level."));

		vectorStore.add(documents);
		logger.info("DataIngestion : Sample documents loaded into VectorStore.");

		}
	}

}
