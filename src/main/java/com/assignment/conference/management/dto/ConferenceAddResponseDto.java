package com.assignment.conference.management.dto;

import lombok.Data;

@Data
public class ConferenceAddResponseDto {
    private Long id;
    private String name;
    private String startTime;
    private String description;
    private Long maxEntries;
}
