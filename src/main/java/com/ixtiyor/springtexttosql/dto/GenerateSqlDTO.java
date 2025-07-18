package com.ixtiyor.springtexttosql.dto;


import com.ixtiyor.springtexttosql.enums.AiProviderEnum;

public record GenerateSqlDTO(
        AiProviderEnum provider,
        String model,
        String message
) {
}
