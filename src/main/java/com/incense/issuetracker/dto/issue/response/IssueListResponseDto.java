package com.incense.issuetracker.dto.issue.response;

import com.incense.issuetracker.domain.issue.Issue;
import com.incense.issuetracker.dto.label.response.LabelResponseDto;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueListResponseDto {

    private Long id;
    private String title;
    private String isOpened;
    private String date;
    private String writerName;
    private String writerImage;
    private String milestoneTitle;
    private int commentCount;
    private List<LabelResponseDto> labels;

    //정적 팩토리 메서드
    public static IssueListResponseDto from(Issue issue) {
        return IssueListResponseDto.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .isOpened(issue.getIsOpened())
                .date(issue.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy:MM:dd")))
                .writerName(issue.getWriter().getEmail())
                .writerImage(issue.getWriter().getProfileImage())
                .milestoneTitle(issue.getMilestone().getTitle())
                .commentCount(issue.getComments().size())
                .labels(issue.getIssueLabels().stream().map(issueLabel -> LabelResponseDto.from(issueLabel.getLabel())).collect(Collectors.toList()))
                .build();
    }
}
