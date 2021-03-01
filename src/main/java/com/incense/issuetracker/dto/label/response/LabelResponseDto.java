package com.incense.issuetracker.dto.label.response;

import com.incense.issuetracker.domain.label.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelResponseDto {

    private Long id;
    private String title;
    private String description;
    private String backgroundColor;
    private String textColor;

    public static LabelResponseDto from(Label label) {
        return LabelResponseDto.builder()
                .id(label.getId())
                .title(label.getTitle())
                .description(label.getDescription())
                .backgroundColor(label.getBackgroundColor())
                .textColor(label.getTextColor())
                .build();
    }
}
