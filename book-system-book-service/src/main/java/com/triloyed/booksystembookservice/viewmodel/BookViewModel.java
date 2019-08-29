package com.triloyed.booksystembookservice.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class BookViewModel {

  private int bookId;
  @Size(min = 1, max = 50, message = "TITLE should be between 1 and 50 charcaters.")
  @NotEmpty(message = "Please provide a value for TITLE.")
  private String title;
  @Size(min = 1, max = 50, message = "AUTHOR should be between 1 and 50 characters.")
  @NotEmpty(message = "Please provide a value for AUTHOR.")
  private String author;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookViewModel that = (BookViewModel) o;
    return bookId == that.bookId &&
            title.equals(that.title) &&
            author.equals(that.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookId, title, author);
  }

  @Override
  public String toString() {
    return "BookViewModel{" +
            "bookId=" + bookId +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            '}';
  }
}
