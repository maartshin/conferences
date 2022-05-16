package com.assignment.conference.management.dto;

import com.assignment.conference.validator.date.DateValid;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ConferenceAddRequestDto {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @DateValid(value = "yyyy-MM-dd")
    private String startTime;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @Range(min = 0, max = 10_000)
    private Long maxEntries;

}
