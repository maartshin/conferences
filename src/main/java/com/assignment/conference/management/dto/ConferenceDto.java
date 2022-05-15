package com.assignment.conference.management.dto;

import com.assignment.conference.validator.date.DateValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConferenceDto {

    @NotBlank
    private String name;

    @NotNull
    @DateValid(value = "yyyy-MM-dd")
    private String startTime;

    @NotBlank
    private String description;

}
