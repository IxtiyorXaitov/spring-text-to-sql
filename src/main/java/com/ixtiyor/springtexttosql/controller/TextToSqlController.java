package com.ixtiyor.springtexttosql.controller;

import com.ixtiyor.springtexttosql.dto.GenerateSqlDTO;
import com.ixtiyor.springtexttosql.service.TextToSqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/sql")
@RestController
@RequiredArgsConstructor
public class TextToSqlController {

    private final TextToSqlService service;

    @PostMapping("/generate")
    public String generateSql(
            @RequestBody GenerateSqlDTO dto
    ) {
        return service.generateSql(dto.provider(), dto.model(), dto.message());
    }
}
