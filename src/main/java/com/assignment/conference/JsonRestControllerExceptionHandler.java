package com.assignment.conference;

import com.assignment.conference.management.dto.RestErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class JsonRestControllerExceptionHandler extends ResponseEntityExceptionHandler {


    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .error(messageSource.getMessage("general.validation.message", null, Locale.getDefault()))
                .fieldErrorList(convertFieldErrors(ex))
                .build();
        return new ResponseEntity<>(restErrorDto, HttpStatus.BAD_REQUEST);
    }

    private List<RestErrorDto.FieldErrorDto> convertFieldErrors(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors().stream()
                .map(error -> RestErrorDto.FieldErrorDto.builder()
                        .field(error.getField())
                        .error(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }

}
