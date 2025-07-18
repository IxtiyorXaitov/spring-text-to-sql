package com.ixtiyor.springtexttosql.controller;

import com.ixtiyor.springtexttosql.dto.GenerateSqlDTO;
import com.ixtiyor.springtexttosql.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final MainService service;

    @PostMapping
    public Object generateSql(
            @RequestBody GenerateSqlDTO dto
    ) {
        return service.test(dto.provider(), dto.model(), dto.message());

    }
}
