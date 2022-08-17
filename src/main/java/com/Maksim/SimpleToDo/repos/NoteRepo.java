package com.Maksim.SimpleToDo.repos;

import com.Maksim.SimpleToDo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {

}

