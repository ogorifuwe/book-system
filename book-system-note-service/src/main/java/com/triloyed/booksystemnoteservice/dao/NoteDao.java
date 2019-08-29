package com.triloyed.booksystemnoteservice.dao;

import com.triloyed.booksystemnoteservice.model.Note;

import java.util.List;

public interface NoteDao {

  Note createNote(Note note);
  Note getNote(int noteId);
  Note getNoteByBook(int bookId);
  List<Note> getAllNotes();
  void updateNote(Note note);
  void deleteNote(int noteId);

}
