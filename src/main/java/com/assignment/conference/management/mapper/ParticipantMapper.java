package com.assignment.conference.management.mapper;

import com.assignment.conference.management.dto.ParticipantRegisterRequestDto;
import com.assignment.conference.management.dto.ParticipantRegisterResponseDto;
import com.assignment.conference.management.entity.ParticipantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
     ParticipantEntity mapToEntity(ParticipantRegisterRequestDto requestDto);
     ParticipantRegisterResponseDto mapToDto(ParticipantEntity participantEntity);
}
