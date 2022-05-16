package com.assignment.conference.management.dto;

import lombok.Data;

@Data
public class ConferenceDetailsResponseDto {
    private String name;
    private String startTime;
    private String description;
    private Long entries;
    private Long maxEntries;
}
