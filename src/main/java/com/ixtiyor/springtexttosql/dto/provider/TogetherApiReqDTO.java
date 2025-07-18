package com.ixtiyor.springtexttosql.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TogetherApiReqDTO {

    private String model;
    private List<Message> messages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }

    public TogetherApiReqDTO(String model, String systemPrompt, String userPrompt) {
        this.model = model;
        this.messages = List.of(
                new Message("system", systemPrompt),
                new Message("user", userPrompt)
        );
    }
}
