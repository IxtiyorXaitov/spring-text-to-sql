package com.ixtiyor.springtexttosql.service;

import com.ixtiyor.springtexttosql.enums.AiProviderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private final TextToSqlService textToSqlService;
    private final SqlService sqlService;


    public Object test(AiProviderEnum provider, String model, String userMessage) {
        String response = textToSqlService.generateSql(provider, model, userMessage);

        return sqlService.extractAndRunSelectForGemini(provider, response);
    }
}
