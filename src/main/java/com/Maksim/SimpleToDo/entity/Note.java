package com.Maksim.SimpleToDo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note_table")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    private String description;

    private String endTime;

    private boolean doneMark;

    private Long userId;
}
