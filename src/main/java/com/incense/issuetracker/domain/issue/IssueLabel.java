package com.incense.issuetracker.domain.issue;

import com.incense.issuetracker.domain.label.Label;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_label_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "label_id")
    private Label label;

    @Builder
    public IssueLabel(Issue issue, Label label) {
        this.issue = issue;
        this.label = label;
    }
}
