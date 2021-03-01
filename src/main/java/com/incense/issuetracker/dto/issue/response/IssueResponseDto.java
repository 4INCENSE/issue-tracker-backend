package com.incense.issuetracker.dto.issue.response;

import com.incense.issuetracker.domain.issue.Issue;
import com.incense.issuetracker.dto.label.response.LabelResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueResponseDto {

    private Long id;
    private String title;
    private String isOpened;
    private String date;
    private String writerName;
    private String writerImage;
    private String milestoneTitle;
    private List<LabelResponseDto> labels;

    //정적 팩토리 메서드
    public static IssueResponseDto from(Issue issue) {
        return IssueResponseDto.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .isOpened(issue.getIsOpened())
                .date(issue.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy:MM:dd")))
                .writerName(issue.getWriter().getName())
                .writerImage(issue.getWriter().getProfileImage())
                .milestoneTitle(issue.getMilestone().getTitle())
                .labels(issue.getIssueLabels().stream().map(issueLabel -> LabelResponseDto.from(issueLabel.getLabel())).collect(Collectors.toList()))
                .build();
    }
}
