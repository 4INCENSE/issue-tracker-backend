package com.incense.issuetracker.controller;

import com.incense.issuetracker.domain.issue.Issue;
import com.incense.issuetracker.dto.issue.response.IssueListResponseDto;
import com.incense.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueListResponseDto>> list() {
        List<Issue> issues = issueService.findIssues();
        return ResponseEntity.ok(issues.stream().map(issue -> IssueListResponseDto.from(issue)).collect(Collectors.toList()));
    }
}
