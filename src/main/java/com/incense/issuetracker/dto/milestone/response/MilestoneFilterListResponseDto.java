package com.incense.issuetracker.dto.milestone.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MilestoneFilterListResponseDto {

    private Long id;
    private String title;

}
