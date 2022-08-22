package com.Maksim.SimpleToDo.exception;

public class NoEntityException extends Exception {
    public NoEntityException(Long noteId) {
        super("Note with id = " + noteId + " not found.");
    }
}
