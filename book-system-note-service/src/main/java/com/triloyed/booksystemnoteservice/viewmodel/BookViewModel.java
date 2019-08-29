package com.triloyed.booksystemnoteservice.viewmodel;

import com.triloyed.booksystemnoteservice.model.Note;

import java.util.List;
import java.util.Objects;

public class BookViewModel {

  private int bookId;
  private String title;
  private String author;
  private List<Note> noteList;

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public List<Note> getNoteList() {
    return noteList;
  }

  public void setNoteList(List<Note> noteList) {
    this.noteList = noteList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookViewModel that = (BookViewModel) o;
    return bookId == that.bookId &&
            title.equals(that.title) &&
            author.equals(that.author) &&
            noteList.equals(that.noteList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookId, title, author, noteList);
  }

  @Override
  public String toString() {
    return "BookViewModel{" +
            "bookId=" + bookId +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", noteList=" + noteList +
            '}';
  }
}