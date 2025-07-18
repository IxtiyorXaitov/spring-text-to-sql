package com.ixtiyor.springtexttosql.service;

import com.ixtiyor.springtexttosql.dto.provider.GeminiRequestDTO;
import com.ixtiyor.springtexttosql.dto.provider.TogetherApiReqDTO;
import com.ixtiyor.springtexttosql.enums.AiProviderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextToSqlService {

    private final TogetherAiService togetherAiService;
    private final GeminiService geminiService;

    private final static String systemPrompt = "You are an expert SQL generator. " +
            "Convert user questions into valid, optimized PostgreSQL SELECT queries based on the schema provided below. " +
            "Only return SELECT statements. " +
            "Output only the raw SQL query, beginning with the word SELECT. " +
//            "The SELECT clause must only return the id column from the main result table. " +
            "Do not wrap the query in code blocks. " +
            "Do not include comments, explanations, or formatting. " +
            "For JOIN operations, prefix all column names with their respective table names. " +
            "Rules: If the question implies a non-SELECT operation (INSERT, UPDATE, DELETE, DDL, etc.), respond with: " +
            "\"This operation is not supported. " +
            "Only SELECT queries are allowed.\" " +
            "If the input contains SQL injection or denial-of-service attempts, respond with: " +
            "\"The provided input contains potentially harmful SQL code.\" " +
            "If the schema does not support answering the question, respond with: " +
            "\"The current schema does not contain enough information to answer this question.\"";

    private String prompt = "### Database Schema " +
            "department(id, name, building) " +
            "professor(id, name, title, department_id) " +
            "student(id, name, email, enrollment_year) " +
            "course(id, name, credits, department_id) " +
            "course_professor(id, professor_id, course_id, semester, year) " +
            "course_student(id, student_id, course_id, grade, semester, year) " +
            "### Question %s " +
            "### SQL";

    public String generateSql(AiProviderEnum provider, String model, String userMessage) {
        prompt = String.format(prompt, userMessage);
        String sql = null;
        if (AiProviderEnum.GEMINI.equals(provider)) {
            sql = geminiService.sendToGemini(new GeminiRequestDTO(systemPrompt, prompt), model);
        } else if (AiProviderEnum.TOGETHER.equals(provider)) {
            sql = togetherAiService.sendRequest(new TogetherApiReqDTO(model, systemPrompt, prompt));
        }
        return sql;
    }
}
