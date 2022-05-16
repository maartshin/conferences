package com.assignment.conference.management.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ParticipantRegisterRequestDto {

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size(max = 50)
    @NotBlank
    private String email;

}
