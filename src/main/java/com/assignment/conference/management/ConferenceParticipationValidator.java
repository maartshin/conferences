package com.assignment.conference.management;

import com.assignment.conference.management.entity.ConferenceEntity;

public class ConferenceParticipationValidator {

    public static void validate(ConferenceEntity entity) {
        if (ConferenceStatus.CANCELLED.name().equals(entity.getStatus())) {
            throw new IllegalStateException("Conference is canceled, cannot register.");
        }
        if (entity.getMaxEntries() == entity.getParticipants().size()) {
            throw new IllegalStateException("Conference is full, cannot register.");
        }
    }

}
