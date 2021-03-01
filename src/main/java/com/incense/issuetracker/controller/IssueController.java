package com.incense.issuetracker.controller;

import com.incense.issuetracker.dto.issue.response.IssueResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> list() {
        return null;
    }
}
