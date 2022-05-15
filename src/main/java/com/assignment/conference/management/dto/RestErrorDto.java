package com.assignment.conference.management.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestErrorDto {

    private String error;

    private List<FieldErrorDto> fieldErrorList;

    @Builder
    @Data
    public static final class FieldErrorDto {
        private String field;
        private String error;
    }
}
