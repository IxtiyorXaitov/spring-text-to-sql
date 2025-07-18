package com.ixtiyor.springtexttosql.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixtiyor.springtexttosql.dto.provider.GeminiResponse;
import com.ixtiyor.springtexttosql.enums.AiProviderEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SqlService {
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    public List<?> extractAndRunSelectForGemini(AiProviderEnum provider, String response) {
        String sql = null;
        if (AiProviderEnum.GEMINI.equals(provider))
            sql = extractSelectForGemini(response);
        if (AiProviderEnum.TOGETHER.equals(provider))
            sql = extractSelectForTogether(response);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    private String extractSelectForGemini(String response) {
        GeminiResponse geminiResponse = null;
        try {
            geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String sql = geminiResponse.candidates.getFirst().content.parts.getFirst().text.trim().replaceAll("[\\n\\r]+", " ");
        System.err.println(sql);
        if (!sql.toUpperCase().startsWith("SELECT")) {
            throw new IllegalArgumentException("Only SELECT statements are allowed.");
        }
        return sql;
    }

    private String extractSelectForTogether(String response) {
        JsonNode root = null;
        try {
            root = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode choices = root.path("choices");
        if (choices.isEmpty()) {
            throw new IllegalArgumentException("Invalid response: missing choices");
        }

        String sql = choices.get(0).path("message").path("content").asText().trim();
        System.err.println(sql);
        sql = sql.replaceAll("[\\r\\n]+", " ").replaceAll(" +", " ").trim();

        if (!sql.toUpperCase().startsWith("SELECT")) {
            throw new IllegalArgumentException("Only SELECT statements are allowed.");
        }

        return sql;
    }
}
