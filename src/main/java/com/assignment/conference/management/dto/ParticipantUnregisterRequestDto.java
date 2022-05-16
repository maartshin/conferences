package com.assignment.conference.management.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParticipantUnregisterRequestDto {
    @NotNull
    private Long participantId;
}
