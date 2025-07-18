package com.ixtiyor.springtexttosql.config;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class OkHttpClientBeans {
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder();
        okhttpClient.connectTimeout(Duration.ofMinutes(5));
        okhttpClient.readTimeout(Duration.ofMinutes(5));
        okhttpClient.writeTimeout(Duration.ofMinutes(5));
        return new OkHttpClient(okhttpClient);
    }
}
