package com.triloyed.booksystemnoteservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Note {

 private int noteId;
 //@NotEmpty(message = "Please provide a value for BOOK ID.")
 private int bookId;
 @Size(min = 1, max = 255, message = "Content of NOTE should be between 1 and 255 characters most.")
 @NotEmpty(message = "Please provide a value for NOTE.")
 private String note;

  public int getNoteId() {
    return noteId;
  }

  public void setNoteId(int noteId) {
    this.noteId = noteId;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Note note1 = (Note) o;
    return noteId == note1.noteId &&
            bookId == note1.bookId &&
            note.equals(note1.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(noteId, bookId, note);
  }

  @Override
  public String toString() {
    return "Note{" +
            "noteId=" + noteId +
            ", bookId=" + bookId +
            ", note='" + note + '\'' +
            '}';
  }

}
