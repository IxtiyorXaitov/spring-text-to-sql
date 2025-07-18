package com.ixtiyor.springtexttosql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeminiRequestDTO {

    public List<Content> contents;
    public SystemInstruction systemInstruction;

    public GeminiRequestDTO(String systemPrompt, String userPrompt) {
        this.contents = List.of(new Content(
                "user",
                List.of(new Part(userPrompt))
        ));
        this.systemInstruction = new SystemInstruction(
                "system",
                List.of(new Part(systemPrompt))
        );
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content {
        public String role;
        public List<Part> parts;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Part {
        public String text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SystemInstruction {
        public String role;
        public List<Part> parts;
    }

}
