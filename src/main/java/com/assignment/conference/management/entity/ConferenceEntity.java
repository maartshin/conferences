package com.assignment.conference.management.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Conference")
public class ConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "startTime")
    private LocalDate startTime;

    @Column(name = "description")
    private String description;

}
