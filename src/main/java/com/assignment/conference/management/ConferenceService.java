package com.assignment.conference.management;

import com.assignment.conference.management.dto.ConferenceDto;
import com.assignment.conference.management.entity.ConferenceEntity;
import com.assignment.conference.management.mapper.ConferenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final ConferenceMapper conferenceMapper;

    public ConferenceEntity addConference(ConferenceDto conferenceDto) {
        ConferenceEntity entity = conferenceMapper.mapToEntity(conferenceDto);
        return conferenceRepository.save(entity);
    }

    public List<ConferenceEntity> getAllConferences() {
        return conferenceRepository.findAll();
    }

}
