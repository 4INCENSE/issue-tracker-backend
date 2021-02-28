package com.incense.issuetracker.domain.milestone;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long id;

    private String title;

    private String description;

    private String dueBy;

    private int openIssueCount;

    private int closedIssueCount;

    private char isOpened;

    @Builder
    public Milestone(String title, String description, String dueBy) {
        this.title = title;
        this.description = description;
        this.dueBy = dueBy;
        this.openIssueCount = 0;
        this.closedIssueCount = 0;
        this.isOpened = 'y';
    }
}
