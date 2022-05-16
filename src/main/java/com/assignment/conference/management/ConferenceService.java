package com.assignment.conference.management;

import com.assignment.conference.management.dto.*;
import com.assignment.conference.management.entity.ConferenceEntity;
import com.assignment.conference.management.entity.ParticipantEntity;
import com.assignment.conference.management.mapper.ConferenceMapper;
import com.assignment.conference.management.mapper.ParticipantMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final ConferenceMapper conferenceMapper;

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    @Transactional
    public ConferenceAddResponseDto addConference(ConferenceAddRequestDto conferenceDto) {
        ConferenceEntity mappedEntity = conferenceMapper.mapConferenceAddRequestToEntity(conferenceDto);
        mappedEntity.setStatus(ConferenceStatus.DEFAULT.name());
        ConferenceEntity savedEntity = conferenceRepository.save(mappedEntity);
        return conferenceMapper.mapEntityToConferenceAddResponse(savedEntity);
    }

    public List<ConferenceDetailsResponseDto> getAllConferences() {
        return conferenceRepository.findAll().stream()
                .map(this::createConferenceDetailsResponseDto)
                .collect(Collectors.toList());
    }

    public ConferenceDetailsResponseDto getConference(Long id) {
        ConferenceEntity entity = conferenceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return createConferenceDetailsResponseDto(entity);
    }

    @Transactional
    public ParticipantRegisterResponseDto addParticipant(
        Long id,
        ParticipantRegisterRequestDto participantRegisterRequestDto
    ) {
        ConferenceEntity conferenceEntity = conferenceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        ConferenceParticipationValidator.validate(conferenceEntity);
        ParticipantEntity participantEntity = participantMapper.mapToEntity(participantRegisterRequestDto);
        participantEntity.setConference(conferenceEntity);
        ParticipantEntity savedParticipant = participantRepository.save(participantEntity);
        return participantMapper.mapToDto(savedParticipant);
    }

    @Transactional
    public ConferenceDetailsResponseDto cancelConference(ConferenceCancelRequestDto requestDto) {
        ConferenceEntity conferenceEntity = conferenceRepository.findById(requestDto.getConferenceId())
                .orElseThrow(EntityNotFoundException::new);
        conferenceEntity.setStatus(ConferenceStatus.CANCELED.name());
        conferenceRepository.save(conferenceEntity);
        return createConferenceDetailsResponseDto(conferenceEntity);
    }


    public void removeParticipant(ParticipantUnregisterRequestDto requestDto) {
        ParticipantEntity participantEntity = participantRepository.findById(requestDto.getParticipantId())
                .orElseThrow(EntityNotFoundException::new);
        participantRepository.delete(participantEntity);
    }

    private ConferenceDetailsResponseDto createConferenceDetailsResponseDto(ConferenceEntity entity) {
        ConferenceDetailsResponseDto responseDto = conferenceMapper.mapEntityToConferenceDetailsResponseDto(entity);
        responseDto.setEntries((long) entity.getParticipants().size());
        return responseDto;
    }
}
