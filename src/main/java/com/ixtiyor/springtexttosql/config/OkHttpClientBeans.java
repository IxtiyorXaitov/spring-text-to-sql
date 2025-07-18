package com.ixtiyor.springtexttosql.config;

import com.ixtiyor.springtexttosql.properties.GeminiAiProperties;
import com.ixtiyor.springtexttosql.properties.TogetherAiProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class OkHttpClientBeans {

    @Bean(name = "togetherOkHttpClient")
    public OkHttpClient togetherOkHttpClient(TogetherAiProperties properties) {
        return new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(properties.getTimeout()))
                .readTimeout(Duration.ofSeconds(properties.getTimeout()))
                .writeTimeout(Duration.ofSeconds(properties.getTimeout()))
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request requestWithHeaders = original.newBuilder()
                            .addHeader("Authorization", "Bearer " + properties.getApiKey())
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(requestWithHeaders);
                })
                .build();
    }

    @Bean(name = "geminiOkHttpClient")
    public OkHttpClient geminiOkHttpClient(GeminiAiProperties properties) {
        return new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(properties.getTimeout()))
                .readTimeout(Duration.ofSeconds(properties.getTimeout()))
                .writeTimeout(Duration.ofSeconds(properties.getTimeout()))
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request requestWithHeaders = original.newBuilder()
                            .addHeader("Authorization", "Bearer " + properties.getApiKey())
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(requestWithHeaders);
                })
                .build();
    }

    @Primary
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
