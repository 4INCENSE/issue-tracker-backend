package com.incense.issuetracker.service;

import com.incense.issuetracker.domain.issue.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueService {

    public List<Issue> findIssues() {
        return null;
    }
}
