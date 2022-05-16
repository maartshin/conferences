package com.assignment.conference.management.mapper;

import com.assignment.conference.management.dto.ConferenceAddRequestDto;
import com.assignment.conference.management.dto.ConferenceAddResponseDto;
import com.assignment.conference.management.dto.ConferenceDetailsResponseDto;
import com.assignment.conference.management.entity.ConferenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConferenceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd")
    ConferenceEntity mapConferenceAddRequestToEntity(ConferenceAddRequestDto conferenceDto);
    ConferenceAddResponseDto mapEntityToConferenceAddResponse(ConferenceEntity conferenceEntity);
    ConferenceDetailsResponseDto mapEntityToConferenceDetailsResponseDto(ConferenceEntity conferenceEntity);
}
