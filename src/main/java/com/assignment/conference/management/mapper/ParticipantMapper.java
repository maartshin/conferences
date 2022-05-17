package com.assignment.conference.management.mapper;

import com.assignment.conference.management.dto.ParticipantRegisterRequestDto;
import com.assignment.conference.management.dto.ParticipantRegisterResponseDto;
import com.assignment.conference.management.entity.ParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

     @Mapping(target = "id", ignore = true)
     @Mapping(target = "conference", ignore = true)
     ParticipantEntity mapToEntity(ParticipantRegisterRequestDto requestDto);

     ParticipantRegisterResponseDto mapToDto(ParticipantEntity participantEntity);
}
