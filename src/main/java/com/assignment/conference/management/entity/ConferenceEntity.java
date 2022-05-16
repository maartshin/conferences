package com.assignment.conference.management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CONFERENCE")
public class ConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_TIME")
    private LocalDate startTime;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MAX_ENTRIES")
    private Long maxEntries;

    @OneToMany(mappedBy="conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipantEntity> participants;

}
