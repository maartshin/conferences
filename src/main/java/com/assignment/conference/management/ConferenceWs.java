package com.assignment.conference.management;

import com.assignment.conference.management.dto.ConferenceDto;
import com.assignment.conference.management.entity.ConferenceEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/conference")
public class ConferenceWs {

    private final ConferenceService conferenceService;

    @PostMapping(value = "/new")
    public ResponseEntity<ConferenceEntity> addConference(@RequestBody @Valid ConferenceDto conferenceDto) {
        return new ResponseEntity<>(conferenceService.addConference(conferenceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConferenceEntity>> getConferenceList() {
        return new ResponseEntity<>(conferenceService.getAllConferences(), HttpStatus.OK);
    }

}
