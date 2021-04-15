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

    private String email;

    private String password;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    private AuthenticationType authType;

    private LocalDateTime deletedAt;

    @Builder
    public User(String name, String email, String password, String profileImage, AuthenticationType authType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.authType = authType;
    }

    public User update(String name, String profile) {
        this.profileImage = profile;
        this.name = name;

        return this;
    }

    @PrePersist
    public void prePersist() {
        if (password == null) {
            password = "";
        }
    }
}
