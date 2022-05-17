package com.assignment.conference.management;

import com.assignment.conference.management.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/conference")
public class ConferenceWs {

    private final ConferenceService conferenceService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/new")
    public ResponseEntity<ConferenceAddResponseDto> addConference(@RequestBody @Valid ConferenceAddRequestDto conferenceDto) {
        return new ResponseEntity<>(conferenceService.addConference(conferenceDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/cancel")
    public ResponseEntity<ConferenceDetailsResponseDto> cancelConference(
            @RequestBody @Valid ConferenceCancelRequestDto requestDto
    ) {
        return new ResponseEntity<>(conferenceService.cancelConference(requestDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ConferenceDetailsResponseDto> getConference(@PathVariable Long id) {
        return new ResponseEntity<>(conferenceService.getConference(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<ConferenceDetailsResponseDto>> getConferenceList() {
        return new ResponseEntity<>(conferenceService.getAllConferences(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/{id}/register")
    public ResponseEntity<ParticipantRegisterResponseDto> addParticipant(
            @PathVariable Long id,
            @RequestBody @Valid ParticipantRegisterRequestDto participantRegisterRequestDto
    ) {
        return new ResponseEntity<>(conferenceService.addParticipant(id, participantRegisterRequestDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/unregister")
    public ResponseEntity<Object> removeParticipant(
            @RequestBody @Valid ParticipantUnregisterRequestDto participantUnregisterRequestDto
    ) {
        conferenceService.removeParticipant(participantUnregisterRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
