package com.incense.issuetracker.domain.issue;

import com.incense.issuetracker.domain.BaseTimeEntity;
import com.incense.issuetracker.domain.milestone.Milestone;
import com.incense.issuetracker.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;

    private String title;

    private String content;

    private char isOpened;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(mappedBy = "issue")
    private List<IssueLabel> issueLabels;

    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees;

    private LocalDateTime deletedAt;

    @Builder
    public Issue(String title, String content) {
        this.title = title;
        this.content = content;
        this.isOpened = 'y';
    }
}
