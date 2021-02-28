package com.incense.issuetracker.domain.user;

import com.incense.issuetracker.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String password;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    private SNS sns_type;

    private LocalDateTime deletedAt;

    @Builder
    public User(String name, String password, String profileImage, SNS sns_type) {
        this.name = name;
        this.password = password;
        this.profileImage = profileImage;
        this.sns_type = sns_type;
    }
}
