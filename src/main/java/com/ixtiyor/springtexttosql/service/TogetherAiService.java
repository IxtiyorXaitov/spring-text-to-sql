package com.ixtiyor.springtexttosql.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixtiyor.springtexttosql.dto.TogetherApiReqDTO;
import com.ixtiyor.springtexttosql.properties.TogetherAiProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TogetherAiService {

    @Qualifier("togetherOkHttpClient")
    private final OkHttpClient client;
    private final TogetherAiProperties properties;
    private final ObjectMapper mapper = new ObjectMapper();


    private String sendRequest(TogetherApiReqDTO prompt) {
        try {
            String body = mapper.writeValueAsString(prompt);

            Request request = new Request.Builder()
                    .url(properties.getHost() + "/v1/chat/completions")
                    .post(RequestBody.create(body, MediaType.parse("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Error: " + response);
                assert response.body() != null;
                return response.body().string();
            }
        } catch (IOException ex) {
            throw new RuntimeException("error");
        }
    }
}
