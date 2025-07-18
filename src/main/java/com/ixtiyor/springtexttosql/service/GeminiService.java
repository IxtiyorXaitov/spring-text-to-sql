package com.ixtiyor.springtexttosql.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixtiyor.springtexttosql.dto.provider.GeminiRequestDTO;
import com.ixtiyor.springtexttosql.properties.GeminiAiProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final OkHttpClient client;
    private final GeminiAiProperties properties;
    private final ObjectMapper mapper = new ObjectMapper();

    public String sendToGemini(GeminiRequestDTO prompt, String model) {
        try {
            String body = mapper.writeValueAsString(prompt);
            System.err.println(body);
            Request request = new Request.Builder()
                    .addHeader("X-goog-api-key", properties.getApiKey())
                    .addHeader("Content-Type", "application/json")
                    .url(properties.getHost() + "/v1beta/models/" + model)
                    .method("POST", RequestBody.create(body, MediaType.parse("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Error: " + response);
                assert response.body() != null;
                return response.body().string();
            }
        } catch (IOException ex) {
            throw new RuntimeException(String.format("error: %s", ex.getMessage()));
        }
    }

}
