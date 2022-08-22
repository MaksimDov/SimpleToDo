package com.Maksim.SimpleToDo.repos;

import com.Maksim.SimpleToDo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> findAll();
    
    List<Note> findAllByUserId(Long userId);
    List<Note> findAllById(Long Id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update note_table set done_mark = ?1 where id = ?2")
    void updateDoneMark(boolean doneMark, Long id);
}

