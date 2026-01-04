
package com.ai.spring_ai_rag.service;

import java.util.List;
import java.util.Objects;
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

//  --1--

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
		if (count == 10) {
			logger.info("DocumentInjectionService : No documents found in Vector Store. Injecting documents...");
			TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(path);
			// List<Document> documents =
			// tikaDocumentReader.readDocumentsFromDirectory("path/to/your/documents");

			List<Document> rawDocs = tikaDocumentReader.read();
			//List<Document> cleanTextDocs = rawDocs.stream().map(doc -> sanitizeDocument(doc)).filter(Objects::nonNull).toList();

			TextSplitter textSplitter = new TokenTextSplitter();
			List<Document> textChunks = textSplitter.split(rawDocs);
			logger.info("DocumentInjectionService : Number of document chunks to be injected: " + textChunks.size());

			//List<Document> safeChunks = textChunks.stream().filter(this::isPureText).toList();
			//logger.info("DocumentInjectionService : Is base64: " + isValidBase64(safeChunks));
			vectorStore.add(textChunks);
			logger.info("DocumentInjectionService : Document injection completed.");
		}

	}

	private Document sanitizeDocument(Document doc) {
		if (doc == null || doc.getText() == null) {
			return null;
		}

		String text = doc.getText();

		// Remove non-printable and binary characters
		text = text.replaceAll("[^\\p{Print}\\p{Space}]", "");

		// Skip garbage / image blobs
		if (text.length() < 50) {
			return null;
		}

		return new Document(text, doc.getMetadata());
	}

	private boolean isPureText(Document doc) {
		return doc.getText().chars().allMatch(c -> c == '\n' || c == '\r' || c == '\t' || (c >= 32 && c <= 126));
	}

	private boolean isValidBase64(List<Document> documents) {
		try {
			logger.info("DocumentInjectionService-isValidBase64 : Validating base64 string..."
					+ documents.get(0).getText());
			logger.info("DocumentInjectionService-isValidBase64 : Validating base64 getFormattedContent..."
					+ documents.get(0).getFormattedContent());
			logger.info("DocumentInjectionService-isValidBase64 : Validating base64 media..."
					+ documents.get(0).getMedia());
			logger.info("DocumentInjectionService-isValidBase64 : Validating base64 metadata..."
					+ documents.get(0).getMetadata());
			java.util.Base64.getDecoder().decode(documents.get(0).getText());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
