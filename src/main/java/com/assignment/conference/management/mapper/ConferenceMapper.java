package com.assignment.conference.management.mapper;

import com.assignment.conference.management.dto.ConferenceDto;
import com.assignment.conference.management.entity.ConferenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConferenceMapper {

    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd")
    ConferenceEntity mapToEntity(ConferenceDto conferenceDto);
    ConferenceDto mapToDto(ConferenceEntity conferenceEntity);
}
