package com.ixtiyor.springtexttosql.service;

import com.ixtiyor.springtexttosql.dto.GeminiRequestDTO;
import com.ixtiyor.springtexttosql.dto.TogetherApiReqDTO;
import com.ixtiyor.springtexttosql.enums.AiProviderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextToSqlService {

    private final TogetherAiService togetherAiService;
    private final GeminiService geminiService;

    private final static String systemPrompt = "You are an expert SQL generator. " +
            "Convert natural language questions into syntactically correct and optimized SQL SELECT queries using the provided database schema. " +
            "- Use standard SQL (PostgreSQL). " +
            "- Output **only the SQL query**. " +
            "- Do not include explanations, comments, or formatting. " +
            "- Assume the schema is correct and up to date.";

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
